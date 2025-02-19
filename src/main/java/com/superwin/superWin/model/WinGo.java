package com.superwin.superWin.model;

import com.superwin.superWin.commonEnum.GameName;
import com.superwin.superWin.commonEnum.GameSessionStatus;
import com.superwin.superWin.commonEnum.GameType;
import com.superwin.superWin.game.lottery.winGo.enums.Color;
import com.superwin.superWin.game.lottery.winGo.enums.Size;
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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
