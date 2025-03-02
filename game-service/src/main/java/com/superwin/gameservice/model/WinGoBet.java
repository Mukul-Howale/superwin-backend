package com.superwin.gameservice.model;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "win_go_bets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class WinGoBet {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "profile_id", nullable = false)
    private UUID profileId;

    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @Column(name = "bet_amount", nullable = false)
    private Long betAmount;

    @Column(name = "number")
    private Integer number;

    @Column(name = "color")
    private Color color;

    @Column(name = "size")
    private Size size;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
