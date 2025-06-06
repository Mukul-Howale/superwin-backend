package com.superwin.gameservice.repository;

import com.superwin.gameservice.enums.Time;
import com.superwin.gameservice.model.WinGoSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.superwin.gameservice.enums.GameSessionStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface WinGoSessionRepository extends JpaRepository<WinGoSession, UUID> {

    @Query("SELECT w FROM WinGoSession w WHERE w.time = :time")
    @NonNull
    Page<WinGoSession> findAllByTime(@NonNull Pageable pageable, @Param("time")Time time);
}
