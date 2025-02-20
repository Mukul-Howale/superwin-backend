package com.superwin.superWin.dto;


import java.util.UUID;

public record BetRequestDTO(
        UUID profileId,
        UUID sessionId,
        Long betAmount

) {
}
