package com.superwin.referralservice.client;

import com.superwin.referralservice.dto.ProfileFilterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.UUID;

@HttpExchange("/profile")
public interface ProfileClient {

    @GetExchange("/{referralCode}")
    public ResponseEntity<ProfileFilterDTO> getByReferralCode(@PathVariable Long referralCode);
}