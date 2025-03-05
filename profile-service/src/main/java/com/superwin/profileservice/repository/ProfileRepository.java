package com.superwin.profileservice.repository;

import com.superwin.profileservice.dto.ProfileFilterDTO;
import com.superwin.profileservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    @Query("SELECT new com.superwin.profileservice.dto.ProfileFilterDTO(p.id, p.referredCode) " +
            "FROM Profile p WHERE p.referralCode = :referredCode")
    Optional<ProfileFilterDTO> getByReferralCode(@Param("referralCode") Long referredCode);
}
