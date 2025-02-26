package com.superwin.gameservice.model;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.Size;
import com.superwin.gameservice.enums.GameSessionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "win_go_sessions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WinGoSession {

    // Game session id
    @Id
    private UUID id = UUID.randomUUID();

    private Long totalAmount;
    private Long minorityAmount;
    private Long majorityAmount;
    private Integer number;
    private Color color;
    private Size size;

    @Enumerated(EnumType.STRING)
    private GameSessionStatus sessionStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
