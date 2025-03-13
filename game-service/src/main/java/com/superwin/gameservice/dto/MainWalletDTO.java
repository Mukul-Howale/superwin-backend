package com.superwin.gameservice.dto;

public record MainWalletDTO(
        Long totalBalance,
        Long mainBalance,
        Long efwBalance,
        Long bonus
) {
}
