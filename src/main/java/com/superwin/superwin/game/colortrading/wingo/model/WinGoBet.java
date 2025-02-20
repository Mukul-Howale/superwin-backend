package com.superwin.superwin.game.colortrading.wingo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinGoBet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long profileId;

    private Long gameId;
    private Long betAmount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
