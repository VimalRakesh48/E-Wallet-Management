package com.example.demo.service.impl;

import com.example.demo.dto.CardRequestDTO;
import com.example.demo.dto.CardResponseDTO;
import com.example.demo.entity.Card;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Override
    public CardResponseDTO addCard(CardRequestDTO request) {

        if (cardRepository.existsByCardNumber(request.getCardNumber())) {
            throw new DuplicateResourceException("Card already exists");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + request.getUserId()));

        Card card = Card.builder()
                .cardNumber(request.getCardNumber())
                .cardHolderName(request.getCardHolderName())
                .expiryDate(request.getExpiryDate())
                .cardType(request.getCardType())
                .active(request.getActive())
                .user(user)
                .build();

        Card savedCard = cardRepository.save(card);

        return CardResponseDTO.builder()
                .cardId(savedCard.getCardId())
                .cardNumber(savedCard.getCardNumber())
                .cardHolderName(savedCard.getCardHolderName())
                .expiryDate(savedCard.getExpiryDate())
                .cardType(savedCard.getCardType())
                .active(savedCard.getActive())
                .userId(savedCard.getUser().getUserId())
                .build();
    }

    @Override
    public CardResponseDTO getCardById(Long cardId) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Card not found with id : " + cardId));

        return CardResponseDTO.builder()
                .cardId(card.getCardId())
                .cardNumber(card.getCardNumber())
                .cardHolderName(card.getCardHolderName())
                .expiryDate(card.getExpiryDate())
                .cardType(card.getCardType())
                .active(card.getActive())
                .userId(card.getUser().getUserId())
                .build();
    }

    @Override
    public List<CardResponseDTO> getAllCards() {

        return cardRepository.findAll()
                .stream()
                .map(card -> CardResponseDTO.builder()
                        .cardId(card.getCardId())
                        .cardNumber(card.getCardNumber())
                        .cardHolderName(card.getCardHolderName())
                        .expiryDate(card.getExpiryDate())
                        .cardType(card.getCardType())
                        .active(card.getActive())
                        .userId(card.getUser().getUserId())
                        .build())
                .toList();
    }

    @Override
    public List<CardResponseDTO> getCardsByUserId(Long userId) {

        return cardRepository.findByUserUserId(userId)
                .stream()
                .map(card -> CardResponseDTO.builder()
                        .cardId(card.getCardId())
                        .cardNumber(card.getCardNumber())
                        .cardHolderName(card.getCardHolderName())
                        .expiryDate(card.getExpiryDate())
                        .cardType(card.getCardType())
                        .active(card.getActive())
                        .userId(card.getUser().getUserId())
                        .build())
                .toList();
    }

    @Override
    public CardResponseDTO updateCard(Long cardId, CardRequestDTO request) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Card not found with id : " + cardId));

        card.setCardNumber(request.getCardNumber());
        card.setCardHolderName(request.getCardHolderName());
        card.setExpiryDate(request.getExpiryDate());
        card.setCardType(request.getCardType());
        card.setActive(request.getActive());

        Card updatedCard = cardRepository.save(card);

        return CardResponseDTO.builder()
                .cardId(updatedCard.getCardId())
                .cardNumber(updatedCard.getCardNumber())
                .cardHolderName(updatedCard.getCardHolderName())
                .expiryDate(updatedCard.getExpiryDate())
                .cardType(updatedCard.getCardType())
                .active(updatedCard.getActive())
                .userId(updatedCard.getUser().getUserId())
                .build();
    }

    @Override
    public void deleteCard(Long cardId) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Card not found with id : " + cardId));

        cardRepository.delete(card);
    }
}