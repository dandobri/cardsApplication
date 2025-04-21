package com.bank.cards.entity;

import com.bank.cards.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email обязателен")
    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Size(min = 8, message = "Твой пароль должен быть хотя бы 8 символов")
    @NotBlank(message = "Пароль обязателен")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "cardHolderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> userCards;
}
