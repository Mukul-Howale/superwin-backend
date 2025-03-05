package com.superwin.profileservice.controller;

import com.superwin.profileservice.dto.ProfileDTO;
import com.superwin.profileservice.dto.ProfileFilterDTO;
import com.superwin.profileservice.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ProfileFilterDTO> getByReferralCode(@PathVariable Long referralCode){
        return new ResponseEntity<>(profileService.getByReferralCode(referralCode), HttpStatus.OK);
    }
}
