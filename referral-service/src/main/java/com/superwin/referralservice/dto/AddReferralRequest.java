package com.superwin.referralservice.dto;

import java.util.UUID;

public record AddReferralRequest(Long referralCode, UUID referredTo) {
}
