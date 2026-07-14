package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponseDTO {

    private Long cardId;

    private String cardNumber;

    private String cardHolderName;

    private LocalDate expiryDate;

    private String cardType;

    private Boolean active;

    private Long userId;
}