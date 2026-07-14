package com.example.demo.service;

import com.example.demo.dto.WalletRequestDTO;
import com.example.demo.dto.WalletResponseDTO;

import java.util.List;

public interface WalletService {

    WalletResponseDTO createWallet(WalletRequestDTO walletRequestDTO);

    WalletResponseDTO getWalletById(Long walletId);

    List<WalletResponseDTO> getAllWallets();

    List<WalletResponseDTO> getWalletsByUserId(Long userId);

    WalletResponseDTO updateWallet(Long walletId, WalletRequestDTO walletRequestDTO);

    void deleteWallet(Long walletId);
}