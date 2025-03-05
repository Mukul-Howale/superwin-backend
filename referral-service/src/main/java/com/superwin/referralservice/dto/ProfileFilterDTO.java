package com.superwin.referralservice.dto;

import java.util.UUID;

public record ProfileFilterDTO(UUID profileId, Long referredCode) {
}
