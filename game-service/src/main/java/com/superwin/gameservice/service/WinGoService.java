package com.superwin.gameservice.service;

import com.superwin.gameservice.client.ProfileClient;
import com.superwin.gameservice.dto.ProfileDTO;
import com.superwin.gameservice.dto.WinGoBetRequestDTO;
import com.superwin.gameservice.dto.WinGoSessionResponseDTO;
import com.superwin.gameservice.enums.GameName;
import com.superwin.gameservice.enums.GameStatus;
import com.superwin.gameservice.enums.GameSessionStatus;
import com.superwin.gameservice.enums.Time;
import com.superwin.gameservice.exception.NoActiveWinGoSessionFoundException;
import com.superwin.gameservice.exception.gameexception.GameNotFoundException;
import com.superwin.gameservice.exception.gameexception.GameUnderMaintenanceException;
import com.superwin.gameservice.exception.ProfileNotFoundException;
import com.superwin.gameservice.exception.NoWinGoSessionFoundException;
import com.superwin.gameservice.model.Game;
import com.superwin.gameservice.model.WinGoSession;
import com.superwin.gameservice.repository.GameRepository;
import com.superwin.gameservice.repository.WinGoBetRepository;
import com.superwin.gameservice.repository.WinGoSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WinGoService {

    private WinGoBetRepository winGoBetRepository;
    private WinGoSessionRepository winGoSessionRepository;
    private GameRepository gameRepository;
    private ProfileClient profileClient;

    public Boolean bet(WinGoBetRequestDTO winGoBetRequestDTO){
        try {
            ResponseEntity<ProfileDTO> profileDTO = profileClient.getById(winGoBetRequestDTO.profileId());
            if (!profileDTO.getStatusCode().isSameCodeAs(HttpStatus.OK))
                throw new ProfileNotFoundException("Profile not found");



            return true;

        } catch (Exception e){
            throw new RuntimeException();
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

            // Get list of recent 11 wingo sessions
            List<WinGoSession> optionalWinGoSessionList = winGoSessionRepository.
                    findAll(PageRequest.of(0, 11))
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
            throw new RuntimeException();
        }
    }
}