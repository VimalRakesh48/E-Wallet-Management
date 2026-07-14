package com.example.demo.controller;

import com.example.demo.dto.TransactionRequestDTO;
import com.example.demo.dto.TransactionResponseDTO;
import com.example.demo.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponseDTO createTransaction(@Valid @RequestBody TransactionRequestDTO request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getTransaction(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/wallet/{walletId}")
    public List<TransactionResponseDTO> getByWallet(@PathVariable Long walletId) {
        return transactionService.getTransactionsByWalletId(walletId);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}