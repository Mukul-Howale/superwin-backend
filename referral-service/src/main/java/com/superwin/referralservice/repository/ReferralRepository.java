package com.superwin.referralservice.repository;

import com.superwin.referralservice.model.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, UUID> {
}
