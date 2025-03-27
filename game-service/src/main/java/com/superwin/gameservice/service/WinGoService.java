package com.superwin.gameservice.service;

import com.superwin.gameservice.client.ProfileClient;
import com.superwin.gameservice.dto.*;
import com.superwin.gameservice.enums.*;
import com.superwin.gameservice.exception.*;
import com.superwin.gameservice.exception.gameexception.GameNotFoundException;
import com.superwin.gameservice.exception.gameexception.GameUnderMaintenanceException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.superwin.gameservice.helper.WinGoBetHelper.*;

@Service
@RequiredArgsConstructor
public class WinGoService {

    private final WinGoBetRepository winGoBetRepository;
    private final WinGoSessionRepository winGoSessionRepository;
    private final GameRepository gameRepository;
    private final ProfileClient profileClient;
    private final ModelMapper mapper;

    private static final Long INITIAL_TOTAL_AMOUNT = 0L;
    private static final Long INITIAL_MINORITY_AMOUNT = 0L;
    private static final Long INITIAL_MAJORITY_AMOUNT = 0L;
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
            List<WinGoSession> optionalWinGoSessionList = winGoSessionRepository.
                    findAllByTime(PageRequest.of(0, 11), time)
                    .getContent();
            if (optionalWinGoSessionList.isEmpty())
                throw new NoWinGoSessionFoundException("No win_go sessions found");

            // Filter out active wingo session and rest of 10 sessions
            Optional<WinGoSession> activeWinGoSession = optionalWinGoSessionList
                    .stream()
                    .filter(winGoSession -> winGoSession.getSessionStatus().equals(GameSessionStatus.ACTIVE))
                    .findFirst();
            if (activeWinGoSession.isEmpty())
                throw new NoActiveWinGoSessionFoundException("No active win_go session found");

            // Return sessions list
            return new WinGoSessionResponseDTO(activeWinGoSession.get(), optionalWinGoSessionList);
        } catch (Exception e){
            throw new GeneralException("Unhandled Exception: WinGoSessionResponseDTO getSessions(Time time), WinGoService" + e);
        }
    }

    public void saveSession(Time time){
        WinGoSession winGoSession = WinGoSession.builder()
                .id(UUID.randomUUID())
                .totalAmount(INITIAL_TOTAL_AMOUNT)
                .minorityAmount(INITIAL_MINORITY_AMOUNT)
                .majorityAmount(INITIAL_MAJORITY_AMOUNT)
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
        
        // call calculationAlgo
        // or
        // call majoritySelectionAlgo
    }


    private void calculationAlgo(){

    }

    private void majoritySelectionAlgo(){
        // Getting every bet in a particular session
        // Sorting bets according to color/size/number
        // Creating a majority out of bets

    }
}