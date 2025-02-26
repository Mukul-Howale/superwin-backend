package com.superwin.gameservice.repository;

import com.superwin.gameservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.superwin.gameservice.enums.GameName;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {

    @Query("SELECT g FROM Game g WHERE g.name = :name")
    Optional<Game> findByName(@Param("name") GameName name);
}
