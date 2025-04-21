package com.bank.cards.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class CardsController {
    @GetMapping("/{user_name}")
    public String getCard(@PathVariable String user_name) {
        return user_name;
    }
}
