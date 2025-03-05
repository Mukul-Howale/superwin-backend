package com.superwin.referralservice.repository;

import com.superwin.referralservice.model.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, UUID> {

    @Query("SELECT Referral FROM Referral r WHERE r.referralCode = :referredCode")
    Optional<Referral> getReferralDetailsByReferralCode(@Param("referredCode") Long referredCode);
}
