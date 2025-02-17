package com.superwin.superWin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "profile")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long userId;

    private Long bonus;
    private Long depositNo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
