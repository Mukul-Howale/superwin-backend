package com.superwin.profileservice.repository;

import com.superwin.profileservice.model.SavingsWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SavingsWalletRepository extends JpaRepository<SavingsWallet, UUID> {
}
