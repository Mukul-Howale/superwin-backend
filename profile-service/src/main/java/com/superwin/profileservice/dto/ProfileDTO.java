package com.superwin.profileservice.dto;

import com.superwin.profileservice.model.MainWallet;
import com.superwin.profileservice.model.ReferralWallet;
import com.superwin.profileservice.model.SavingsWallet;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProfileDTO(
        UUID id,
        Long userId,
        String userName,
        Long referralCode,
        Long referredCode,
        MainWallet mainWallet,
        ReferralWallet referralWallet,
        SavingsWallet savingsWallet,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
