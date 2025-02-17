package com.superwin.superWin.model;

import com.superwin.superWin.enumerator.GameName;
import com.superwin.superWin.enumerator.GameSession;
import com.superwin.superWin.enumerator.GameType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "game")
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private GameName name;
    private GameType type;
    private GameSession session;
}
