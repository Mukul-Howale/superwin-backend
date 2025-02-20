package com.superwin.gameservice.repository;

import com.superwin.gameservice.model.WinGoBet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WinGoBetRepository extends JpaRepository<WinGoBet, UUID> {
}
