package com.etmg.user.dto;

public class RegisterResponse {

    private Long userId;
    private String message;

    // Construtor vazio
    public RegisterResponse() {
    }

    // Construtor com parâmetros
    public RegisterResponse(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    // Getters e Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}