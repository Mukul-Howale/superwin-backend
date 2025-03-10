package com.superwin.gameservice.dto;

import java.util.UUID;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.Size;
import com.superwin.gameservice.enums.Time;

public record WinGoBetRequestDTO(
        UUID profileId,
        UUID sessionId,
        Long betAmount,
        Integer number,
        Color color,
        Size size,
        Time time
) {
}
