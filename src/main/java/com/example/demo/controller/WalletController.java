package com.example.demo.controller;

import com.example.demo.dto.WalletRequestDTO;
import com.example.demo.dto.WalletResponseDTO;
import com.example.demo.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public WalletResponseDTO createWallet(@Valid @RequestBody WalletRequestDTO request) {
        return walletService.createWallet(request);
    }

    @GetMapping("/{id}")
    public WalletResponseDTO getWallet(@PathVariable Long id) {
        return walletService.getWalletById(id);
    }

    @GetMapping
    public List<WalletResponseDTO> getAllWallets() {
        return walletService.getAllWallets();
    }

    @GetMapping("/user/{userId}")
    public List<WalletResponseDTO> getWalletsByUser(@PathVariable Long userId) {
        return walletService.getWalletsByUserId(userId);
    }

    @PutMapping("/{id}")
    public WalletResponseDTO updateWallet(@PathVariable Long id,
                                          @Valid @RequestBody WalletRequestDTO request) {
        return walletService.updateWallet(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
    }
}