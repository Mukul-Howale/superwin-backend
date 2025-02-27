package com.superwin.gameservice.dto;

import com.superwin.gameservice.enums.GameStatus;
import com.superwin.gameservice.enums.GameName;
import com.superwin.gameservice.enums.GameType;

public record GameCreationRequestDTO(GameName gameName, GameType gameType, GameStatus gameStatus) {
}
