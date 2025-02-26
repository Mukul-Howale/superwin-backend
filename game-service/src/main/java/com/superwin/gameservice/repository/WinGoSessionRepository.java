package com.superwin.gameservice.repository;

import com.superwin.gameservice.model.WinGoSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WinGoSessionRepository extends JpaRepository<WinGoSession, UUID> {
}
