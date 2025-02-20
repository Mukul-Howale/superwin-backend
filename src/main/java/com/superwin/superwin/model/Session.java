package com.superwin.superwin.model;

import com.superwin.superwin.commonEnum.GameName;
import com.superwin.superwin.commonEnum.GameSessionStatus;
import com.superwin.superwin.commonEnum.GameType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long gameId;

    private GameName name;
    private GameType type;
    private GameSessionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
