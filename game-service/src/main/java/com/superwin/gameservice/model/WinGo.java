package com.superwin.gameservice.model;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.Size;
import com.superwin.gameservice.enums.GameType;
import com.superwin.gameservice.enums.GameName;
import com.superwin.gameservice.enums.GameSessionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "win_go")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WinGo {

    // Game session id
    @Id
    private UUID id;

    private Long gameId;

    private GameName name;
    private GameType type;
    private GameSessionStatus status;

    private Long totalAmount;
    private Long minorityAmount;
    private Long majorityAmount;
    private Integer number;
    private Color color;
    private Size size;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
