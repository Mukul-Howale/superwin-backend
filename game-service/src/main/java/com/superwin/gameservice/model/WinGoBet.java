package com.superwin.gameservice.model;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.Size;
import com.superwin.gameservice.enums.Time;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "win_go_bets", schema = "game_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class WinGoBet {

    @Id
    private UUID id;

    @Column(name = "profile_id", nullable = false, updatable = false)
    private UUID profileId;

    @Column(name = "session_id", nullable = false, updatable = false)
    private UUID sessionId;

    @Column(name = "bet_amount", nullable = false, updatable = false)
    private Long betAmount;

    @Column(name = "number", nullable = false, updatable = false)
    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false, updatable = false)
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "size",nullable = false, updatable = false)
    private Size size;

    @Enumerated(EnumType.STRING)
    @Column(name = "time", nullable = false, updatable = false)
    private Time time;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
