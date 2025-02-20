package com.superwin.gameservice.dto;


import java.util.UUID;

public record WinGoBetRequestDTO(
        UUID profileId,
        UUID sessionId,
        Long betAmount

) {
}
