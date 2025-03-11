package com.superwin.profileservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "main_wallet", schema = "profile_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners((AuditingEntityListener.class))
public class MainWallet {

    @Id
    private UUID id;

}
