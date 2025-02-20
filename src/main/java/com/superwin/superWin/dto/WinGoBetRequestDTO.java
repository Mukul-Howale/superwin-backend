package com.superwin.superWin.dto;


import java.util.UUID;

public record WinGoBetRequestDTO(
        UUID profileId,
        UUID sessionId,
        Long betAmount

) {
}
