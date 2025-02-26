package com.superwin.gameservice.dto;

import com.superwin.gameservice.model.WinGoSession;

import java.util.List;

public record WinGoSessionResponseDTO (
        WinGoSession winGoSession,
        List<WinGoSession> winGoSessionList
){}
