package com.superwin.gameservice.dto;

import com.superwin.gameservice.model.WinGoBet;

import java.util.List;

public record WinGoBetResponseDTO(
        WinGoBet pendingWinGoBet,

        List<WinGoBet> winGoBets
) {
}
