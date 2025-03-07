package com.superwin.gameservice.model;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.Size;
import com.superwin.gameservice.enums.GameSessionStatus;
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
@Table(name = "win_go_sessions", schema = "game_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class WinGoSession {

    @Id
    private UUID id;

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @Column(name = "minority_amount", nullable = false)
    private Long minorityAmount;

    @Column(name = "majority_amount", nullable = false)
    private Long majorityAmount;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private Size size;

    @Enumerated(EnumType.STRING)
    @Column(name = "time", nullable = false)
    private Time time;

    @Enumerated(EnumType.STRING)
    @Column(name = "session_status", nullable = false)
    private GameSessionStatus sessionStatus;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
