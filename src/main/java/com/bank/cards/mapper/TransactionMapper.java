package com.bank.cards.mapper;

import com.bank.cards.dto.TransactionRequestDto;
import com.bank.cards.dto.TransactionResponseDto;
import com.bank.cards.entity.Card;
import com.bank.cards.entity.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public static Transaction toEntity(TransactionRequestDto dto, Card fromCard) {
        Transaction tx = new Transaction();
        tx.setAmount(dto.getAmount());
        tx.setType(dto.getType());
        tx.setDescription(dto.getDescription());
        tx.setTimestamp(LocalDateTime.now());
        tx.setCard(fromCard);
        return tx;
    }

    public static TransactionResponseDto toDto(Transaction tx) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setId(tx.getId());
        dto.setType(tx.getType());
        dto.setAmount(tx.getAmount());
        dto.setDescription(tx.getDescription());
        dto.setTimestamp(tx.getTimestamp());
        dto.setCardId(tx.getCard().getId());
        return dto;
    }
}
