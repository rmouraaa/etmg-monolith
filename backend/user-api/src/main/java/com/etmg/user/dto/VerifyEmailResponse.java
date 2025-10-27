package com.etmg.user.dto;

public class VerifyEmailResponse {

    private String message;

    // Construtor vazio
    public VerifyEmailResponse() {
    }

    // Construtor com parâmetros
    public VerifyEmailResponse(String message) {
        this.message = message;
    }

    // Getters e Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}