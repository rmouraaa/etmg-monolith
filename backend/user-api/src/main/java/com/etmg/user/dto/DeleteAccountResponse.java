package com.etmg.user.dto;

public class DeleteAccountResponse {

    private String message;

    // Construtor vazio
    public DeleteAccountResponse() {
    }

    // Construtor com parâmetros
    public DeleteAccountResponse(String message) {
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