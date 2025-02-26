package com.superwin.referralservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "referral")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Referral {

    @Id
    private UUID id = UUID.randomUUID();


}
