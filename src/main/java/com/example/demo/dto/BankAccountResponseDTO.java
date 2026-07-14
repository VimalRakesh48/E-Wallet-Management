package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountResponseDTO {

    private Long bankAccountId;

    private String bankName;

    private String accountHolderName;

    private String accountNumber;

    private String ifscCode;

    private Boolean primaryAccount;

    private Long userId;

    private Long walletId;
}