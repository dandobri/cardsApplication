package com.bank.cards.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
    private String password;
}
