package com.superwin.gameservice.service;

import com.superwin.gameservice.client.ProfileClient;
import com.superwin.gameservice.dto.ProfileDTO;
import com.superwin.gameservice.dto.WinGoBetRequestDTO;
import com.superwin.gameservice.dto.WinGoSessionResponseDTO;
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
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WinGoService {

    private WinGoBetRepository winGoBetRepository;
    private WinGoSessionRepository winGoSessionRepository;
    private GameRepository gameRepository;
    private ProfileClient profileClient;

    private static final Integer INITIAL_NUMBER = -1;

    /**
     * For color, size and number, frontend should send null for the rest
     */
    public Boolean bet(WinGoBetRequestDTO winGoBetRequestDTO){
        try {
            // check if valid profile id
            ResponseEntity<ProfileDTO> responseProfileDTO = profileClient.getById(winGoBetRequestDTO.profileId());
            if (!responseProfileDTO.getStatusCode().isSameCodeAs(HttpStatus.OK))
                throw new ProfileNotFoundException("Profile not found");
            ProfileDTO profileDTO = responseProfileDTO.getBody();

            // check if valid session id
            if(winGoSessionRepository.findById(winGoBetRequestDTO.sessionId()).isEmpty())
                throw new NoWinGoSessionFoundException("No win_go sessions found");

            // check if valid time
            Arrays.stream(Time.values()).forEach(time -> {
                if (!time.equals(winGoBetRequestDTO.time())) throw new InvalidBetException("Illegal time used");});

            // check if valid bet amount
            // check main wallet amount before placing a bet
            if(winGoBetRequestDTO.betAmount() <= 0) throw new InvalidBetException("Illegal bet amount");

            // check if any one bet is placed from color, size, number
            if(checkIfColorIsValid(winGoBetRequestDTO.color()))
                setValueAndSaveBet(winGoBetRequestDTO, winGoBetRequestDTO.color(), null, null);
            else if(checkIfSizeIsValid(winGoBetRequestDTO.size()))
                setValueAndSaveBet(winGoBetRequestDTO, null, winGoBetRequestDTO.size(), null);
            else if(checkIfNumberIsValid(winGoBetRequestDTO.number()))
                setValueAndSaveBet(winGoBetRequestDTO, null, null, winGoBetRequestDTO.number());
            else throw new InvalidBetException("Invalid bet placed");

            return true;

        } catch (Exception e){
            throw new GeneralException("Unhandled Exception: Boolean bet(WinGoBetRequestDTO winGoBetRequestDTO), WinGoService" + e);
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

    private boolean checkIfSizeIsValid(Size size){
        Arrays.stream(Size.values()).forEach(s -> {
            if(!s.equals(size)) throw new InvalidBetException("Invalid size");
        });
        return true;
    }

    private boolean checkIfColorIsValid(Color color){
        Arrays.stream(Color.values()).forEach(c -> {
            if(!c.equals(color)) throw new InvalidBetException("Invalid color");
        });
        return true;
    }

    private boolean checkIfNumberIsValid(Integer number){
        if(number < 0 || number > 9)
            throw new InvalidBetException("Invalid number");
        return true;
    }

    private void setValueAndSaveBet(WinGoBetRequestDTO winGoBetRequestDTO, Color color, Size size, Integer number){
        WinGoBet winGoBet = WinGoBet.builder()
                .id(UUID.randomUUID())
                .profileId(winGoBetRequestDTO.profileId())
                .sessionId(winGoBetRequestDTO.sessionId())
                .time(winGoBetRequestDTO.time())
                .betAmount(winGoBetRequestDTO.betAmount())
                .color(color)
                .size(size)
                .number(number)
                .build();
        winGoBetRepository.save(winGoBet);
    }
}