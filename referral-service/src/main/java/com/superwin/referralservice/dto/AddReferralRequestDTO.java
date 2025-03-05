package com.superwin.referralservice.dto;

import java.util.UUID;

public record AddReferralRequestDTO(Long referredCode, UUID referredTo) {
}
