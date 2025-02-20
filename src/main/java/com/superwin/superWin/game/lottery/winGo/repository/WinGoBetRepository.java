package com.superwin.superWin.game.lottery.winGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WinGoBetRepository extends JpaRepository<WinGoBet, UUID> {
}
