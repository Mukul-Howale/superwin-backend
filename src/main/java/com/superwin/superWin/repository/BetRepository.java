package com.superwin.superWin.repository;

import com.superwin.superWin.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BetRepository extends JpaRepository<Bet, UUID> {
}
