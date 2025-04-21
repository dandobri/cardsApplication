package com.bank.cards.mapper;

import com.bank.cards.dto.CardCreateDto;
import com.bank.cards.dto.CardResponseDto;
import com.bank.cards.entity.Card;
import com.bank.cards.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public static Card toEntity(CardCreateDto dto, String encryptedNumber, String last4, User user) {
        Card card = new Card();
        card.setEncryptedCardNumber(encryptedNumber);
        card.setLast4CardDigits(last4);
        card.setExpiryDate(dto.getExpiryDate());
        card.setCardHolderId(user);
        return card;
    }
    public static CardResponseDto toDto(Card card) {
        CardResponseDto dto = new CardResponseDto();
        dto.setId(card.getId());
        dto.setMaskedCardNumber(card.getMaskedCardNumber());
        dto.setBalance(card.getBalance());
        dto.setExpiryDate(card.getExpiryDate());
        dto.setStatus(card.getStatus().name());
        return dto;
    }
}
