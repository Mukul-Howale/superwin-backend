package com.superwin.profileservice.dto;

import java.util.UUID;

public record ProfileFilterDTO(UUID profileId, Long referredCode) {
}
