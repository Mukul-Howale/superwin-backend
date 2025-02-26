package com.superwin.gameservice.dto;

import com.superwin.gameservice.enums.GameStatus;
import com.superwin.gameservice.enums.GameName;
import com.superwin.gameservice.enums.GameType;

public record GameCreaationRequestDTO(GameName gameName, GameType gameType, GameStatus status) {
}
