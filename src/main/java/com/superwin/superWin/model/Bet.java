package com.superwin.superWin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bet")
@Data
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long profileId;

    private Long gameId;
    private Long betAmount;
}
