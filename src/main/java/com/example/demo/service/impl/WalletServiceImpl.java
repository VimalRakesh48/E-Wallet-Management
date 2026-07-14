package com.example.demo.service.impl;

import com.example.demo.dto.WalletRequestDTO;
import com.example.demo.dto.WalletResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.Wallet;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Override
    public WalletResponseDTO createWallet(WalletRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + request.getUserId()));

        Wallet wallet = Wallet.builder()
                .walletName(request.getWalletName())
                .balance(request.getBalance())
                .walletType(request.getWalletType())
                .active(request.getActive())
                .user(user)
                .build();

        Wallet savedWallet = walletRepository.save(wallet);

        return WalletResponseDTO.builder()
                .walletId(savedWallet.getWalletId())
                .walletName(savedWallet.getWalletName())
                .balance(savedWallet.getBalance())
                .walletType(savedWallet.getWalletType())
                .active(savedWallet.getActive())
                .userId(savedWallet.getUser().getUserId())
                .build();
    }

    @Override
    public WalletResponseDTO getWalletById(Long walletId) {

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found with id : " + walletId));

        return WalletResponseDTO.builder()
                .walletId(wallet.getWalletId())
                .walletName(wallet.getWalletName())
                .balance(wallet.getBalance())
                .walletType(wallet.getWalletType())
                .active(wallet.getActive())
                .userId(wallet.getUser().getUserId())
                .build();
    }

    @Override
    public List<WalletResponseDTO> getAllWallets() {

        return walletRepository.findAll()
                .stream()
                .map(wallet -> WalletResponseDTO.builder()
                        .walletId(wallet.getWalletId())
                        .walletName(wallet.getWalletName())
                        .balance(wallet.getBalance())
                        .walletType(wallet.getWalletType())
                        .active(wallet.getActive())
                        .userId(wallet.getUser().getUserId())
                        .build())
                .toList();
    }

    @Override
    public List<WalletResponseDTO> getWalletsByUserId(Long userId) {

        return walletRepository.findByUserUserId(userId)
                .stream()
                .map(wallet -> WalletResponseDTO.builder()
                        .walletId(wallet.getWalletId())
                        .walletName(wallet.getWalletName())
                        .balance(wallet.getBalance())
                        .walletType(wallet.getWalletType())
                        .active(wallet.getActive())
                        .userId(wallet.getUser().getUserId())
                        .build())
                .toList();
    }

    @Override
    public WalletResponseDTO updateWallet(Long walletId, WalletRequestDTO request) {

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found with id : " + walletId));

        wallet.setWalletName(request.getWalletName());
        wallet.setBalance(request.getBalance());
        wallet.setWalletType(request.getWalletType());
        wallet.setActive(request.getActive());

        Wallet updatedWallet = walletRepository.save(wallet);

        return WalletResponseDTO.builder()
                .walletId(updatedWallet.getWalletId())
                .walletName(updatedWallet.getWalletName())
                .balance(updatedWallet.getBalance())
                .walletType(updatedWallet.getWalletType())
                .active(updatedWallet.getActive())
                .userId(updatedWallet.getUser().getUserId())
                .build();
    }

    @Override
    public void deleteWallet(Long walletId) {

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found with id : " + walletId));

        walletRepository.delete(wallet);
    }
}