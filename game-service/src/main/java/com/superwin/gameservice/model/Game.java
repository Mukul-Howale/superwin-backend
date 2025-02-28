package com.superwin.gameservice.model;

import com.superwin.gameservice.enums.GameName;
import com.superwin.gameservice.enums.GameStatus;
import com.superwin.gameservice.enums.GameType;
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
@Table(name = "games", schema = "game_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Game {

    @Id
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", nullable = false)
    private GameName name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private GameType type;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private GameStatus status;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
