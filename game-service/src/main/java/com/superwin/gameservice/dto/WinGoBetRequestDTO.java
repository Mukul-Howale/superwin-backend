package com.superwin.gameservice.dto;


import java.util.UUID;

public record WinGoBetRequestDTO(
        UUID profileId,
        UUID sessionId,
        Long betAmount

        // which time cycle i.e. 30 seconds, 1 minute, 5 minute
) {
}
