package com.superwin.transactionservice.model;

import com.superwin.transactionservice.enums.TransactionType;
import com.superwin.transactionservice.enums.TransactionStatus;
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
    private UUID id = UUID.randomUUID();

    private Long profileId;

    private TransactionType transactionType;
    private Long amount;
    private String from;
    private String to;
    private TransactionStatus transactionStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
