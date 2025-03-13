package com.superwin.profileservice.controller;

import com.superwin.profileservice.service.MainWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main-wallet")
public class MainWalletController {

    private final MainWalletService mainWalletService;


}
