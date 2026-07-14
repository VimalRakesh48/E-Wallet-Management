package com.example.demo.service;

import com.example.demo.dto.CardRequestDTO;
import com.example.demo.dto.CardResponseDTO;

import java.util.List;

public interface CardService {

    CardResponseDTO addCard(CardRequestDTO cardRequestDTO);

    CardResponseDTO getCardById(Long cardId);

    List<CardResponseDTO> getAllCards();

    List<CardResponseDTO> getCardsByUserId(Long userId);

    CardResponseDTO updateCard(Long cardId, CardRequestDTO cardRequestDTO);

    void deleteCard(Long cardId);
}