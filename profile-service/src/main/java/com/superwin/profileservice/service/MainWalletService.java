package com.superwin.profileservice.service;

import com.superwin.profileservice.dto.MainWalletBalanceUpdateRequest;
import com.superwin.profileservice.repository.MainWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainWalletService {

    private final MainWalletRepository mainWalletRepository;

    public Boolean updateBalance(MainWalletBalanceUpdateRequest mainWalletBalanceUpdateRequest){
        return true;
    }
}
