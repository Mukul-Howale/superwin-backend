package com.superwin.superWin.model.game.lottery.winGo;

import com.superwin.superWin.commonEnum.GameName;
import com.superwin.superWin.commonEnum.GameSessionStatus;
import com.superwin.superWin.commonEnum.GameType;
import com.superwin.superWin.model.game.lottery.winGo.enums.Color;
import com.superwin.superWin.model.game.lottery.winGo.enums.Size;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "win_go")
@Data
public class WinGo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
