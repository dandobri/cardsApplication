package com.bank.cards.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CardResponseDto {
    private Long id;
    private String maskedCardNumber;
    private LocalDate expiryDate;
    private String status;
    private BigDecimal balance;
}
