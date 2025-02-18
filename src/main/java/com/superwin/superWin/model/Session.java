package com.superwin.superWin.model;

import com.superwin.superWin.commonEnum.GameName;
import com.superwin.superWin.commonEnum.GameSessionStatus;
import com.superwin.superWin.commonEnum.GameType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
