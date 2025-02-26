package com.superwin.gameservice.model;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "win_go_bets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinGoBet {

    @Id
    private UUID id = UUID.randomUUID();

    private Long profileId;

    private Long gameId;
    private Long betAmount;
    private Integer number;
    private Color color;
    private Size size;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
