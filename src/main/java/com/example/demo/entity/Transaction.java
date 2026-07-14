package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private String status;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @PrePersist
    public void onCreate() {

        transactionDate = LocalDateTime.now();

        if (status == null) {
            status = "SUCCESS";
        }
    }
}