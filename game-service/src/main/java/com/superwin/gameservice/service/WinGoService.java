package com.superwin.gameservice.service;

import com.superwin.gameservice.client.ProfileClient;
import com.superwin.gameservice.dto.*;
import com.superwin.gameservice.enums.*;
import com.superwin.gameservice.exception.*;
import com.superwin.gameservice.exception.gameexception.GameNotFoundException;
import com.superwin.gameservice.exception.gameexception.GameUnderMaintenanceException;
import com.superwin.gameservice.helper.WinGoPick;
import com.superwin.gameservice.model.Game;
import com.superwin.gameservice.model.WinGoBet;
import com.superwin.gameservice.model.WinGoSession;
import com.superwin.gameservice.repository.GameRepository;
import com.superwin.gameservice.repository.WinGoBetRepository;
import com.superwin.gameservice.repository.WinGoSessionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.superwin.gameservice.helper.WinGoBetHelper.*;

@Service
@RequiredArgsConstructor
public class WinGoService {

    private final WinGoBetRepository winGoBetRepository;
    private final WinGoSessionRepository winGoSessionRepository;
    private final GameRepository gameRepository;
    private final ProfileClient profileClient;
    private final ModelMapper mapper;

    private static final Integer INITIAL_NUMBER = -1;

    /**
     * For color, size and number, frontend should send null for the rest
     *
     * Using Kafka instead of @Async to update wallet balance
     * in profile service as, kafka is more reliable
     */
    @Transactional
    public Boolean bet(WinGoBetRequestDTO winGoBetRequestDTO){
        try {
            // check if valid profile id
            ResponseEntity<ProfileDTO> responseProfileDTO = profileClient.getById(winGoBetRequestDTO.profileId());
            if (!responseProfileDTO.getStatusCode().isSameCodeAs(HttpStatus.OK))
                throw new ProfileNotFoundException("Profile not found");
            MainWalletDTO mainWalletDTO = mapper.map((responseProfileDTO.getBody()).mainWallet(), MainWalletDTO.class);

            // check if valid session id
            if(winGoSessionRepository.findById(winGoBetRequestDTO.sessionId()).isEmpty())
                throw new NoWinGoSessionFoundException("No win_go sessions found");

            // check if valid time
            Arrays.stream(Time.values()).forEach(time -> {
                if (!time.equals(winGoBetRequestDTO.time())) throw new InvalidBetException("Invalid time used");});

            // check if valid bet amount
            if(winGoBetRequestDTO.betAmount() <= 0) throw new InvalidBetException("Invalid bet amount");
            // check main wallet amount before placing a bet
            if(winGoBetRequestDTO.betAmount() > mainWalletDTO.totalBalance()) throw new InvalidBetException("Bet exceed wallet balance");

            // check if any one bet is placed from color, size, number
            if(checkIfColorIsValid(winGoBetRequestDTO.color()))
                winGoBetRepository.save(buildWinGoBet(winGoBetRequestDTO, winGoBetRequestDTO.color(), null, null));
            else if(checkIfSizeIsValid(winGoBetRequestDTO.size()))
                winGoBetRepository.save(buildWinGoBet(winGoBetRequestDTO, null, winGoBetRequestDTO.size(), null));
            else if(checkIfNumberIsValid(winGoBetRequestDTO.number()))
                winGoBetRepository.save(buildWinGoBet(winGoBetRequestDTO, null, null, winGoBetRequestDTO.number()));
            else throw new InvalidBetException("Invalid bet placed");

            // TO-DO:
            // Using kafka to update wallet balance in profile service
            // Bet amount will be sent to the profile service, so new balance can be calculated

            return true;

        } catch (Exception e){
            throw new GeneralException("Unhandled Exception: Boolean bet(WinGoBetRequestDTO winGoBetRequestDTO), WinGoService" + e);
        }
    }

    public List<WinGoBet> getBets(UUID profileId){
        try {
            // Get list of recent 11 wingo bets based on profile id
            List<WinGoBet> optionalWinGoBet = winGoBetRepository.
                    findAllByProfileId(PageRequest.of(0, 11), profileId)
                    .getContent();
            if (optionalWinGoBet.isEmpty())
                throw new NoWinGoBetsFoundException("No win_go bets found");
            return optionalWinGoBet;
        } catch (Exception e){
            throw new GeneralException("Unhandled Exception: WinGoBetResponseDTO getBets(UUID profileId), WinGoService" + e);
        }
    }

    public WinGoSessionResponseDTO getSessions(Time time){
        try{
            // Get game details by game name
            Optional<Game> optionalGame = gameRepository.findByName(GameName.WIN_GO);
            if(optionalGame.isEmpty())
                throw new GameNotFoundException("Game not found");

            // Check if game is playable or under maintenance
            if(optionalGame.get().getStatus().equals(GameStatus.UNDER_MAINTENANCE))
                throw new GameUnderMaintenanceException("Game is under maintenance");

            // Get list of recent 11 wingo sessions based on time period
            List<WinGoSession> optionalWinGoSessionList = getWinGoSessionList(0,11,time);

            // Filter out active wingo session and rest of 10 sessions
            WinGoSession activeWinGoSession = getActiveWinGoSession(optionalWinGoSessionList);

            // Return sessions list
            return new WinGoSessionResponseDTO(activeWinGoSession, optionalWinGoSessionList);
        } catch (Exception e){
            throw new GeneralException("Unhandled Exception: WinGoSessionResponseDTO getSessions(Time time), WinGoService" + e);
        }
    }

    public void saveSession(Time time){
        WinGoSession winGoSession = WinGoSession.builder()
                .id(UUID.randomUUID())
                .number(INITIAL_NUMBER)
                .color(Color.INITIAL_COLOR)
                .size(Size.INITIAL_SIZE)
                .time(time)
                .sessionStatus(GameSessionStatus.ACTIVE)
                .build();
        winGoSessionRepository.save(winGoSession);
    }

    // pick -> a specific outcome for a particular session
    public void sessionPick(Time time){
        WinGoSession activeWinGoSession = getActiveWinGoSession(getWinGoSessionList(0,1,time));
        List<WinGoBet> winGoBets = winGoBetRepository.findAllBySessionIdAndResult(activeWinGoSession.getId(), String.valueOf(Result.PENDING));
        int random = ThreadLocalRandom.current().nextInt(10);
        if(winGoBets.isEmpty() || random == 5) randomPickCalculator(activeWinGoSession);
        majoritySelection(activeWinGoSession, winGoBets);
    }


    private void randomPickCalculator(WinGoSession activeWinGoSession){
        Integer pick = ThreadLocalRandom.current().nextInt(10);
        updateWinGoSession(activeWinGoSession, pick);
    }

    private void majoritySelection(WinGoSession activeWinGoSession, List<WinGoBet> winGoBets){
        LinkedHashMap<String, Long> totalAmountPerCategory = new LinkedHashMap<>();
        Long[] numberAmountPerPick = new Long[10];
        totalAmountPerPick(winGoBets,totalAmountPerCategory,numberAmountPerPick);

        LinkedHashMap<String, Long> sortedAmountCombination = sortLowToHigh(getCategoryCombinationAmount(totalAmountPerCategory));

        Integer selectedPick = selectPick(sortedAmountCombination, numberAmountPerPick);

        updateWinGoSession(activeWinGoSession, selectedPick);
    }

    /**
     * Iterating through sortedAmountCombination
     * Getting number picks for each combination starting from the smallest
     * Checking if any single number bets are made on the smallest pick
     * And if the single number bet amount win is greater than the smallest pick
     * If no, then select the pick
     * If yes, move to the next combination
     */
    private Integer selectPick(LinkedHashMap<String, Long> sortedAmountCombination, Long[] numberAmountPerPick){
        Integer selectedPick = null;
        for(String key : sortedAmountCombination.keySet()){
            List<Integer> smallestAmountPicks = WinGoPick.getPickCombo(key);
            Collections.shuffle(smallestAmountPicks);
            for(Integer n : smallestAmountPicks){
                if(numberAmountPerPick[n] == null || (numberAmountPerPick[n] * 10) < sortedAmountCombination.get(key)) {
                    selectedPick = n;
                    break;
                }
            }
            if(selectedPick != null) break;
        }
        return selectedPick;
    }

    private List<WinGoSession> getWinGoSessionList(Integer pageNumber, Integer pageSize, Time time){
        List<WinGoSession> optionalWinGoSessionList = winGoSessionRepository.
                findAllByTime(PageRequest.of(pageNumber, pageSize), time)
                .getContent();
        if (optionalWinGoSessionList.isEmpty())
            throw new NoWinGoSessionFoundException("No win_go sessions found");

        return optionalWinGoSessionList;
    }

    private WinGoSession getActiveWinGoSession(List<WinGoSession> optionalWinGoSessionList){
        Optional<WinGoSession> optionalActiveWinGoSession = optionalWinGoSessionList
                .stream()
                .filter(winGoSession -> winGoSession.getSessionStatus().equals(GameSessionStatus.ACTIVE))
                .findFirst();
        if (optionalActiveWinGoSession.isEmpty())
            throw new NoActiveWinGoSessionFoundException("No active win_go session found");

        return optionalActiveWinGoSession.get();
    }

    private void updateWinGoSession(WinGoSession activeWinGoSession, Integer selectedPick){
        activeWinGoSession.setNumber(selectedPick);
        activeWinGoSession.setColor(WinGoPick.getColor(selectedPick));
        activeWinGoSession.setSize(WinGoPick.getSize(selectedPick));
        winGoSessionRepository.save(activeWinGoSession);

        updateBetsAfterSessionResult(activeWinGoSession,selectedPick);
    }

    private void totalAmountPerPick(List<WinGoBet> winGoBets, LinkedHashMap<String, Long> totalAmountPerCategory, Long[] numberAmountPerPick){
        for (WinGoBet bet : winGoBets){
            if(bet.getColor().equals(Color.RED)) totalAmountPerCategory.merge(String.valueOf(Color.RED), bet.getBetAmount(), Long::sum);
            if(bet.getColor().equals(Color.GREEN)) totalAmountPerCategory.merge(String.valueOf(Color.GREEN), bet.getBetAmount(), Long::sum);
            if(bet.getSize().equals(Size.SMALL)) totalAmountPerCategory.merge(String.valueOf(Size.SMALL), bet.getBetAmount(), Long::sum);
            if(bet.getSize().equals(Size.BIG)) totalAmountPerCategory.merge(String.valueOf(Size.BIG), bet.getBetAmount(), Long::sum);

            numberAmountPerPick[bet.getNumber()] = numberAmountPerPick[bet.getNumber()] + bet.getBetAmount();
        }
    }

    private LinkedHashMap<String, Long> getCategoryCombinationAmount(LinkedHashMap<String, Long> totalAmountPerCategory){
        LinkedHashMap<String, Long> categoryCombinationAmount = new LinkedHashMap<>();
        categoryCombinationAmount.put("smallRed", totalAmountPerCategory.get(String.valueOf(Size.SMALL)) + totalAmountPerCategory.get(String.valueOf(Color.RED)));
        categoryCombinationAmount.put("smallGreen", totalAmountPerCategory.get(String.valueOf(Size.SMALL)) + totalAmountPerCategory.get(String.valueOf(Color.GREEN)));
        categoryCombinationAmount.put("bigRed", totalAmountPerCategory.get(String.valueOf(Size.BIG)) + totalAmountPerCategory.get(String.valueOf(Color.RED)));
        categoryCombinationAmount.put("bigGreen", totalAmountPerCategory.get(String.valueOf(Size.BIG)) + totalAmountPerCategory.get(String.valueOf(Color.GREEN)));
        return categoryCombinationAmount;
    }

    private LinkedHashMap<String, Long> sortLowToHigh(LinkedHashMap<String, Long> categoryCombinationAmount){
        List<Map.Entry<String, Long>> entryList = new ArrayList<>(categoryCombinationAmount.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        LinkedHashMap<String, Long> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Async
    protected void updateBetsAfterSessionResult(WinGoSession activeWinGoSession, Integer selectedPick){
        List<WinGoBet> winGoBets = winGoBetRepository.findAllBySessionIdAndResult(activeWinGoSession.getId(), String.valueOf(Result.PENDING));
        List<WinGoBet> updatedWinGoBets = new ArrayList<>();
        for (WinGoBet bet : winGoBets){
            if((!Objects.equals(bet.getNumber(), INITIAL_NUMBER) && Objects.equals(bet.getNumber(), selectedPick))
                    || (!Objects.equals(bet.getColor(),Color.INITIAL_COLOR) && Objects.equals(bet.getColor(), WinGoPick.getColor(selectedPick)))
                    || (!Objects.equals(bet.getSize(), Size.INITIAL_SIZE) && Objects.equals(bet.getSize(), WinGoPick.getSize(selectedPick))))
                bet.setResult(Result.WON);
            else bet.setResult(Result.LOST);
            updatedWinGoBets.add(bet);
        }
        winGoBetRepository.saveAll(updatedWinGoBets);

        // Calculate and change wallet balance
    }
}