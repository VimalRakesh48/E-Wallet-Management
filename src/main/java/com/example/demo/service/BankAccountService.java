package com.example.demo.service;

import com.example.demo.dto.BankAccountRequestDTO;
import com.example.demo.dto.BankAccountResponseDTO;

import java.util.List;

public interface BankAccountService {

    BankAccountResponseDTO addBankAccount(BankAccountRequestDTO bankAccountRequestDTO);

    BankAccountResponseDTO getBankAccountById(Long bankAccountId);

    List<BankAccountResponseDTO> getAllBankAccounts();

    List<BankAccountResponseDTO> getBankAccountsByUserId(Long userId);

    BankAccountResponseDTO updateBankAccount(Long bankAccountId, BankAccountRequestDTO bankAccountRequestDTO);

    void deleteBankAccount(Long bankAccountId);
}