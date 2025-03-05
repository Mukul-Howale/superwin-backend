package com.superwin.referralservice.dto;

import java.util.UUID;

// referredCode -> code to be referred by another user
// referredTo -> id of the user using the referredCode
public record AddReferralRequestDTO(Long referredCode, UUID referredTo) {
}
