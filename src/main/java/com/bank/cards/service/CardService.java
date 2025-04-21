package com.bank.cards.service;

import com.bank.cards.dto.CardCreateDto;
import com.bank.cards.dto.CardResponseDto;
import com.bank.cards.entity.Card;
import com.bank.cards.entity.User;
import com.bank.cards.mapper.CardMapper;
import com.bank.cards.repository.CardRepository;
import com.bank.cards.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;

    public CardService(CardRepository cardRepository,
                       UserRepository userRepository, EncryptionService encryptionService) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
    }

    public CardResponseDto createCard(CardCreateDto dto) throws Exception {
        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Пользователь не найден");
        }
        String encryptedNumber = encryptionService.encrypt(dto.getCardNumber());
        String last4 = dto.getCardNumber().substring(dto.getCardNumber().length() - 4);
        Card card = CardMapper.toEntity(dto, encryptedNumber, last4, userOpt.get());
        card = cardRepository.save(card);
        return CardMapper.toDto(card);
    }
}
