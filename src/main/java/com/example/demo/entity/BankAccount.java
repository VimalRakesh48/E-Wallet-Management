package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankAccountId;

    @NotBlank(message = "Bank name is required")
    @Column(nullable = false)
    private String bankName;

    @NotBlank(message = "Account holder name is required")
    @Column(nullable = false)
    private String accountHolderName;

    @NotBlank(message = "Account number is required")
    @Column(nullable = false, unique = true)
    private String accountNumber;

    @NotBlank(message = "IFSC code is required")
    @Column(nullable = false)
    private String ifscCode;

    @Column(nullable = false)
    private Boolean primaryAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @PrePersist
    public void onCreate() {
        if (primaryAccount == null) {
            primaryAccount = false;
        }
    }
}