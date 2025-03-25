package com.superwin.gameservice.repository;

import com.superwin.gameservice.model.WinGoBet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface WinGoBetRepository extends JpaRepository<WinGoBet, UUID> {

    @Query("SELECT w FROM WinGoBet  w WHERE w.profileId = :profileId")
    @NonNull
    Page<WinGoBet> findAllByProfileId(@NonNull Pageable pageable, @Param("profileId") UUID profileId);

}
