package com.bank.cards.entity;

import com.bank.cards.enums.CardStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String encryptedCardNumber;

    @Column(length = 4, nullable = false)
    private String last4CardDigits;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardStatus status = CardStatus.ACTIVE;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User cardHolderId;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal limitMoneyToSpend = BigDecimal.valueOf(Long.MAX_VALUE);

    public String getMaskedCardNumber() {
        return "**** **** **** " + last4CardDigits;
    }
}
