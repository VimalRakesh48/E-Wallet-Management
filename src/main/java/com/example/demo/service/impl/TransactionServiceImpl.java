package com.example.demo.service.impl;

import com.example.demo.dto.TransactionRequestDTO;
import com.example.demo.dto.TransactionResponseDTO;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.Wallet;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO request) {

        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found with id : " + request.getWalletId()));

        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .transactionType(request.getTransactionType())
                .description(request.getDescription())
                .wallet(wallet)
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionResponseDTO.builder()
                .transactionId(savedTransaction.getTransactionId())
                .amount(savedTransaction.getAmount())
                .transactionType(savedTransaction.getTransactionType())
                .status(savedTransaction.getStatus())
                .description(savedTransaction.getDescription())
                .transactionDate(savedTransaction.getTransactionDate())
                .walletId(savedTransaction.getWallet().getWalletId())
                .build();
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long transactionId) {

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Transaction not found with id : " + transactionId));

        return TransactionResponseDTO.builder()
                .transactionId(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .status(transaction.getStatus())
                .description(transaction.getDescription())
                .transactionDate(transaction.getTransactionDate())
                .walletId(transaction.getWallet().getWalletId())
                .build();
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {

        return transactionRepository.findAll()
                .stream()
                .map(transaction -> TransactionResponseDTO.builder()
                        .transactionId(transaction.getTransactionId())
                        .amount(transaction.getAmount())
                        .transactionType(transaction.getTransactionType())
                        .status(transaction.getStatus())
                        .description(transaction.getDescription())
                        .transactionDate(transaction.getTransactionDate())
                        .walletId(transaction.getWallet().getWalletId())
                        .build())
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByWalletId(Long walletId) {

        return transactionRepository.findByWalletWalletId(walletId)
                .stream()
                .map(transaction -> TransactionResponseDTO.builder()
                        .transactionId(transaction.getTransactionId())
                        .amount(transaction.getAmount())
                        .transactionType(transaction.getTransactionType())
                        .status(transaction.getStatus())
                        .description(transaction.getDescription())
                        .transactionDate(transaction.getTransactionDate())
                        .walletId(transaction.getWallet().getWalletId())
                        .build())
                .toList();
    }

    @Override
    public void deleteTransaction(Long transactionId) {

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Transaction not found with id : " + transactionId));

        transactionRepository.delete(transaction);
    }
}