package com.superwin.profileservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "main_wallets", schema = "profile_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners((AuditingEntityListener.class))
public class MainWallet {

    @Id
    private UUID id;

    @Column(name = "total_balance", nullable = false)
    private Long totalBalance;

    @Column(name = "main_balance", nullable = false)
    private Long mainBalance;

    @Column(name = "efw_balance", nullable = false)
    private Long efwBalance;

    @Column(name = "bonus", nullable = false)
    private Long bonus;

    @Column(name = "deposit_no", nullable = false)
    private Long depositNo;

    @OneToOne(mappedBy = "mainWallet", fetch = FetchType.LAZY)
    private Profile profile;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
