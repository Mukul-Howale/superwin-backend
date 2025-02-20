package com.superwin.superwin.game.colortrading.wingo.model;

import com.superwin.superwin.game.colortrading.wingo.enums.Color;
import com.superwin.superwin.game.colortrading.wingo.enums.Size;
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

    private Long totalAmount;
    private Long minorityAmount;
    private Long majorityAmount;
    private Integer number;
    private Color color;
    private Size size;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
