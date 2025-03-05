package com.superwin.gameservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProfileDTO(
        UUID id,
        Long userId,
        Long bonus,
        Long depositNo,
        Long referralCode,
        Long referredCode,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
