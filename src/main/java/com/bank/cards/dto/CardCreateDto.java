package com.bank.cards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CardCreateDto {
    @NotBlank
    private String cardNumber;

    @NotNull
    private LocalDate expiryDate;

    @NotNull
    private Long userId;
}
