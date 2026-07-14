package com.example.demo.controller;

import com.example.demo.dto.BankAccountRequestDTO;
import com.example.demo.dto.BankAccountResponseDTO;
import com.example.demo.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankaccounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public BankAccountResponseDTO addBankAccount(@Valid @RequestBody BankAccountRequestDTO request) {
        return bankAccountService.addBankAccount(request);
    }

    @GetMapping("/{id}")
    public BankAccountResponseDTO getBankAccount(@PathVariable Long id) {
        return bankAccountService.getBankAccountById(id);
    }

    @GetMapping
    public List<BankAccountResponseDTO> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/user/{userId}")
    public List<BankAccountResponseDTO> getByUser(@PathVariable Long userId) {
        return bankAccountService.getBankAccountsByUserId(userId);
    }

    @PutMapping("/{id}")
    public BankAccountResponseDTO update(@PathVariable Long id,
                                         @Valid @RequestBody BankAccountRequestDTO request) {
        return bankAccountService.updateBankAccount(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
    }
}