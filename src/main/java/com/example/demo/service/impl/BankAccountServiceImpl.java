package com.example.demo.service.impl;

import com.example.demo.dto.BankAccountRequestDTO;
import com.example.demo.dto.BankAccountResponseDTO;
import com.example.demo.entity.BankAccount;
import com.example.demo.entity.User;
import com.example.demo.entity.Wallet;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public BankAccountResponseDTO addBankAccount(BankAccountRequestDTO request) {

        if (bankAccountRepository.existsByAccountNumber(request.getAccountNumber())) {
            throw new DuplicateResourceException("Bank account already exists");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + request.getUserId()));

        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found with id : " + request.getWalletId()));

        BankAccount bankAccount = BankAccount.builder()
                .bankName(request.getBankName())
                .accountHolderName(request.getAccountHolderName())
                .accountNumber(request.getAccountNumber())
                .ifscCode(request.getIfscCode())
                .primaryAccount(request.getPrimaryAccount())
                .user(user)
                .wallet(wallet)
                .build();

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return BankAccountResponseDTO.builder()
                .bankAccountId(savedBankAccount.getBankAccountId())
                .bankName(savedBankAccount.getBankName())
                .accountHolderName(savedBankAccount.getAccountHolderName())
                .accountNumber(savedBankAccount.getAccountNumber())
                .ifscCode(savedBankAccount.getIfscCode())
                .primaryAccount(savedBankAccount.getPrimaryAccount())
                .userId(savedBankAccount.getUser().getUserId())
                .walletId(savedBankAccount.getWallet().getWalletId())
                .build();
    }

    @Override
    public BankAccountResponseDTO getBankAccountById(Long bankAccountId) {

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank Account not found with id : " + bankAccountId));

        return BankAccountResponseDTO.builder()
                .bankAccountId(bankAccount.getBankAccountId())
                .bankName(bankAccount.getBankName())
                .accountHolderName(bankAccount.getAccountHolderName())
                .accountNumber(bankAccount.getAccountNumber())
                .ifscCode(bankAccount.getIfscCode())
                .primaryAccount(bankAccount.getPrimaryAccount())
                .userId(bankAccount.getUser().getUserId())
                .walletId(bankAccount.getWallet().getWalletId())
                .build();
    }

    @Override
    public List<BankAccountResponseDTO> getAllBankAccounts() {

        return bankAccountRepository.findAll()
                .stream()
                .map(bankAccount -> BankAccountResponseDTO.builder()
                        .bankAccountId(bankAccount.getBankAccountId())
                        .bankName(bankAccount.getBankName())
                        .accountHolderName(bankAccount.getAccountHolderName())
                        .accountNumber(bankAccount.getAccountNumber())
                        .ifscCode(bankAccount.getIfscCode())
                        .primaryAccount(bankAccount.getPrimaryAccount())
                        .userId(bankAccount.getUser().getUserId())
                        .walletId(bankAccount.getWallet().getWalletId())
                        .build())
                .toList();
    }

    @Override
    public List<BankAccountResponseDTO> getBankAccountsByUserId(Long userId) {

        return bankAccountRepository.findByUserUserId(userId)
                .stream()
                .map(bankAccount -> BankAccountResponseDTO.builder()
                        .bankAccountId(bankAccount.getBankAccountId())
                        .bankName(bankAccount.getBankName())
                        .accountHolderName(bankAccount.getAccountHolderName())
                        .accountNumber(bankAccount.getAccountNumber())
                        .ifscCode(bankAccount.getIfscCode())
                        .primaryAccount(bankAccount.getPrimaryAccount())
                        .userId(bankAccount.getUser().getUserId())
                        .walletId(bankAccount.getWallet().getWalletId())
                        .build())
                .toList();
    }

    @Override
    public BankAccountResponseDTO updateBankAccount(Long bankAccountId, BankAccountRequestDTO request) {

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank Account not found with id : " + bankAccountId));

        bankAccount.setBankName(request.getBankName());
        bankAccount.setAccountHolderName(request.getAccountHolderName());
        bankAccount.setAccountNumber(request.getAccountNumber());
        bankAccount.setIfscCode(request.getIfscCode());
        bankAccount.setPrimaryAccount(request.getPrimaryAccount());

        BankAccount updatedBankAccount = bankAccountRepository.save(bankAccount);

        return BankAccountResponseDTO.builder()
                .bankAccountId(updatedBankAccount.getBankAccountId())
                .bankName(updatedBankAccount.getBankName())
                .accountHolderName(updatedBankAccount.getAccountHolderName())
                .accountNumber(updatedBankAccount.getAccountNumber())
                .ifscCode(updatedBankAccount.getIfscCode())
                .primaryAccount(updatedBankAccount.getPrimaryAccount())
                .userId(updatedBankAccount.getUser().getUserId())
                .walletId(updatedBankAccount.getWallet().getWalletId())
                .build();
    }

    @Override
    public void deleteBankAccount(Long bankAccountId) {

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank Account not found with id : " + bankAccountId));

        bankAccountRepository.delete(bankAccount);
    }
}