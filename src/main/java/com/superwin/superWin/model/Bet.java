package com.superwin.superWin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long profileId;

    private Long gameId;
    private Long betAmount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
