package com.superwin.profileservice.controller;

import com.superwin.profileservice.dto.ProfileDTO;
import com.superwin.profileservice.dto.ProfileFilterDTO;
import com.superwin.profileservice.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable UUID id){
        return new ResponseEntity<>(profileService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/{referralCode}")
    public ResponseEntity<ProfileFilterDTO> getProfileFilterByReferralCode(@PathVariable Long referralCode){
        return new ResponseEntity<>(profileService.getProfileFilterByReferralCode(referralCode), HttpStatus.OK);
    }

    @PostMapping("/create/{userId}/{referredCode}")
    public ResponseEntity<ProfileDTO> createProfile(@PathVariable UUID userId, @PathVariable Long referredCode){
        return new ResponseEntity<>(profileService.createProfile(userId, referredCode), HttpStatus.CREATED);
    }
}
