package com.etmg.user.dto;

public class VerifyEmailRequest {

    private String code;

    // Construtor vazio
    public VerifyEmailRequest() {
    }

    // Construtor com parâmetros
    public VerifyEmailRequest(String code) {
        this.code = code;
    }

    // Getters e Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}