package com.superwin.gameservice.service;

import com.superwin.gameservice.dto.GameCreationRequestDTO;
import com.superwin.gameservice.model.Game;
import com.superwin.gameservice.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    private GameRepository gameRepository;

    public Boolean addGame(GameCreationRequestDTO gameCreationRequestDTO){
        try {
            if (gameCreationRequestDTO == null ||
                    gameCreationRequestDTO.gameName() == null ||
                    gameCreationRequestDTO.gameType() == null ||
                    gameCreationRequestDTO.gameStatus() == null
            ) {throw new IllegalArgumentException("Invalid game data received!");}

            Game game = Game.builder()
                    .id(UUID.randomUUID())
                    .name(gameCreationRequestDTO.gameName())
                    .type(gameCreationRequestDTO.gameType())
                    .status(gameCreationRequestDTO.gameStatus())
                    .build();
            gameRepository.save(game);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
