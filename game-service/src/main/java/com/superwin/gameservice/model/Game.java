package com.superwin.gameservice.model;

import com.superwin.gameservice.enums.GameName;
import com.superwin.gameservice.enums.GameSessionStatus;
import com.superwin.gameservice.enums.GameStatus;
import com.superwin.gameservice.enums.GameType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "games")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private GameName name;
    private GameType type;
    private GameStatus status;
}
