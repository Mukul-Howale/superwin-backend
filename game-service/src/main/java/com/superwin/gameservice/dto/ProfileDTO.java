package com.superwin.gameservice.dto;

import com.superwin.profileservice.model.MainWallet;
import com.superwin.profileservice.model.ReferralWallet;
import com.superwin.profileservice.model.SavingsWallet;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProfileDTO(
        UUID id,
        Long userId,
        String userName,
        Long bonus,
        Long depositNo,
        Long referralCode,
        Long referredCode,
        MainWallet mainWallet,
        ReferralWallet referralWallet,
        SavingsWallet savingsWallet,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
