package com.etmg.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String hello() {
        return "🚀 ETMG User API is running!";
    }
}
