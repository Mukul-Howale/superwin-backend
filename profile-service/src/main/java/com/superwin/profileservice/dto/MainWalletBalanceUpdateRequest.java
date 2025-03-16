package com.superwin.profileservice.dto;

import java.util.UUID;

public record MainWalletBalanceUpdateRequest(
        UUID profileId,
        Long betAmount
) {
}
