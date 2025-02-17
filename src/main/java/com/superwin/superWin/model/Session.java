package com.superwin.superWin.model;

import com.superwin.superWin.commonEnum.GameName;
import com.superwin.superWin.commonEnum.GameSessionStatus;
import com.superwin.superWin.commonEnum.GameType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "game")
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gameId;

    private GameName name;
    private GameType type;
    private GameSessionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
