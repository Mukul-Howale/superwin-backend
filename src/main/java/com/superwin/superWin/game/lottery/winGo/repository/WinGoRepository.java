package com.superwin.superWin.game.lottery.winGo.repository;

import com.superwin.superWin.game.lottery.winGo.model.WinGo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WinGoRepository extends JpaRepository<WinGo, UUID> {
}
