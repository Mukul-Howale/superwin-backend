package com.superwin.gameservice.service;

import com.superwin.gameservice.dto.GameCreaationRequestDTO;
import com.superwin.gameservice.enums.GameName;
import com.superwin.gameservice.enums.GameStatus;
import com.superwin.gameservice.enums.GameType;
import com.superwin.gameservice.model.Game;
import com.superwin.gameservice.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    private GameRepository gameRepository;

    public Boolean addGame(GameCreaationRequestDTO gameCreaationRequestDTO){
        Game game = Game.builder()
                .id(UUID.randomUUID())
                .name(GameName.WIN_GO)
                .type(GameType.LOTTERY)
                .status(GameStatus.PLAYABLE)
                .build();
        gameRepository.save(game);
        return true;
    }
}
