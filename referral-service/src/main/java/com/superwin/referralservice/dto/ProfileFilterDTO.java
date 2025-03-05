package com.superwin.referralservice.dto;

import java.util.UUID;

// referredCode -> code to be referred by another user
// profileId -> id of the user using the referredCode
public record ProfileFilterDTO(UUID profileId, Long referredCode) {
}
