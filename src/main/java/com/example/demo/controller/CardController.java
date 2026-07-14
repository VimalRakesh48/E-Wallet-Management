package com.example.demo.controller;

import com.example.demo.dto.CardRequestDTO;
import com.example.demo.dto.CardResponseDTO;
import com.example.demo.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public CardResponseDTO addCard(@Valid @RequestBody CardRequestDTO request) {
        return cardService.addCard(request);
    }

    @GetMapping("/{id}")
    public CardResponseDTO getCard(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @GetMapping
    public List<CardResponseDTO> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/user/{userId}")
    public List<CardResponseDTO> getCardsByUser(@PathVariable Long userId) {
        return cardService.getCardsByUserId(userId);
    }

    @PutMapping("/{id}")
    public CardResponseDTO updateCard(@PathVariable Long id,
                                      @Valid @RequestBody CardRequestDTO request) {
        return cardService.updateCard(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}