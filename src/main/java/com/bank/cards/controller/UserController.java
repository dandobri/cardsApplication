package com.bank.cards.controller;

import com.bank.cards.dto.UserRegisterDto;
import com.bank.cards.entity.User;
import com.bank.cards.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registrationUser(@RequestBody @Valid UserRegisterDto user) {
        try {
            User registratedUser = userService.saveUser(user);
            if (registratedUser != null) {
                return ResponseEntity.ok("Registration success with email " + registratedUser.getEmail());
            } else {
                return ResponseEntity.badRequest().body("User with email " + user.getEmail() + " already exists");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid UserRegisterDto user) {
        try {
            User findedUser = userService.getUserByEmail(user.getEmail());
            if (findedUser != null) {
                if (findedUser.getPassword().equals(user.getPassword())) {
                    return ResponseEntity.ok("Login success with email " + findedUser.getEmail());
                } else {
                    return ResponseEntity.badRequest().body(findedUser.getPassword());
                }
            } else {
                return ResponseEntity.badRequest().body("User with email " + user.getEmail() + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
