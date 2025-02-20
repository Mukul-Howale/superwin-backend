package com.superwin.gameservice.repository;

import com.superwin.gameservice.model.WinGo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WinGoRepository extends JpaRepository<WinGo, UUID> {
}
