package com.superwin.profileservice.controller;

import com.superwin.profileservice.dto.MainWalletBalanceUpdateRequest;
import com.superwin.profileservice.service.MainWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main-wallet")
public class MainWalletController {

    private final MainWalletService mainWalletService;

    @PatchMapping("/update-balance")
    public ResponseEntity<Boolean> updateBalance(@RequestBody MainWalletBalanceUpdateRequest mainWalletBalanceUpdateRequest){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
