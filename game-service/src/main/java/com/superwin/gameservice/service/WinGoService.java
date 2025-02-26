package com.superwin.gameservice.service;

import com.superwin.gameservice.client.ProfileClient;
import com.superwin.gameservice.dto.ProfileDTO;
import com.superwin.gameservice.dto.WinGoBetRequestDTO;
import com.superwin.gameservice.dto.WinGoSessionResponseDTO;
import com.superwin.gameservice.enums.GameName;
import com.superwin.gameservice.enums.GameStatus;
import com.superwin.gameservice.enums.GameSessionStatus;
import com.superwin.gameservice.exception.GameNotFoundException;
import com.superwin.gameservice.exception.GameUnderMaintenanceException;
import com.superwin.gameservice.exception.ProfileNotFoundException;
import com.superwin.gameservice.model.Game;
import com.superwin.gameservice.model.WinGoSession;
import com.superwin.gameservice.repository.GameRepository;
import com.superwin.gameservice.repository.WinGoBetRepository;
import com.superwin.gameservice.repository.WinGoSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            if (profileDTO.getStatusCode().isSameCodeAs(HttpStatus.BAD_REQUEST))
                throw new ProfileNotFoundException("Profile not found");



            return true;

        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public WinGoSessionResponseDTO sessionDetails(){
        try{
            Optional<Game> optionalGame = gameRepository.findByName(GameName.WIN_GO);
            if(optionalGame.isEmpty())
                throw new GameNotFoundException("Game not found");

            if(optionalGame.get().getStatus().equals(GameStatus.UNDER_MAINTENANCE))
                throw new GameUnderMaintenanceException("Game is under ");

            Optional<WinGoSession> optionalWinGoSession = winGoSessionRepository.findByStatus(GameSessionStatus.ACTIVE);


        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
