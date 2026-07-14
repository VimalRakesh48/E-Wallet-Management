package com.example.demo.service;

import com.example.demo.dto.TransactionRequestDTO;
import com.example.demo.dto.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {

    TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO);

    TransactionResponseDTO getTransactionById(Long transactionId);

    List<TransactionResponseDTO> getAllTransactions();

    List<TransactionResponseDTO> getTransactionsByWalletId(Long walletId);

    void deleteTransaction(Long transactionId);
}