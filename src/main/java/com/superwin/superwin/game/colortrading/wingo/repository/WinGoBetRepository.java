package com.superwin.superwin.game.colortrading.wingo.repository;

import com.superwin.superwin.game.colortrading.wingo.model.WinGoBet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WinGoBetRepository extends JpaRepository<WinGoBet, UUID> {
}
