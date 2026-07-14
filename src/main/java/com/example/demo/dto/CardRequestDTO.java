package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
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
public class CardRequestDTO {

    @NotBlank(message = "Card number is required")
    private String cardNumber;

    @NotBlank(message = "Card holder name is required")
    private String cardHolderName;

    private LocalDate expiryDate;

    @NotBlank(message = "Card type is required")
    private String cardType;

    private Boolean active;

    private Long userId;
}