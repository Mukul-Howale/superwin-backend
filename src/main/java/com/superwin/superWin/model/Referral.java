package com.superwin.superWin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "referral")
@Data
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
