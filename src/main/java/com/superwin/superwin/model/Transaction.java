package com.superwin.superwin.model;

import com.superwin.superwin.commonEnum.TransactionStatus;
import com.superwin.superwin.commonEnum.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long profileId;

    private TransactionType transactionType;
    private Long amount;
    private String from;
    private String to;
    private TransactionStatus transactionStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
