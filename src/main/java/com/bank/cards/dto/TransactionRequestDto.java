package com.bank.cards.dto;

import com.bank.cards.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDto {
    @NotNull
    private Long fromCardId;
    private Long toCardId;

    @NotNull
    private BigDecimal amount;
    private String description;

    @NotNull
    private TransactionType type;
}
