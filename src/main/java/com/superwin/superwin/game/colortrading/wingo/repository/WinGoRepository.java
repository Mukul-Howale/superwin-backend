package com.superwin.superwin.game.colortrading.wingo.repository;

import com.superwin.superwin.game.colortrading.wingo.model.WinGo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WinGoRepository extends JpaRepository<WinGo, UUID> {
}
