package com.bank.cards.dto;

import com.bank.cards.enums.Role;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private Role role;
}
