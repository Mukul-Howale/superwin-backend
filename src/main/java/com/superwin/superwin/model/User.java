package com.superwin.superwin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long profileId;

    private String email;
    private String phoneNumber;
    private String userName;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
