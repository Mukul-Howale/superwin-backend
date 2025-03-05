package com.superwin.referralservice.controller;

import com.superwin.referralservice.dto.AddReferralRequestDTO;
import com.superwin.referralservice.service.ReferralService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/referral")
@AllArgsConstructor
public class ReferralController {

    private ReferralService referralService;

    @PostMapping("/add")
    public ResponseEntity<String> addReferral(@RequestBody AddReferralRequestDTO addReferralRequestDTO){
        if(!referralService.addReferral(addReferralRequestDTO))
            return new ResponseEntity<>("Referral not added", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>("Referral added", HttpStatus.ACCEPTED);
    }
}
