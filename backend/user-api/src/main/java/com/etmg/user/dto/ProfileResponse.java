package com.etmg.user.dto;

import java.time.LocalDateTime;

public class ProfileResponse {

    private Long userId;
    private String name;
    private String email;
    private Boolean emailVerified;
    private LocalDateTime createdAt;

    // Construtor vazio
    public ProfileResponse() {
    }

    // Construtor com parâmetros
    public ProfileResponse(Long userId, String name, String email, Boolean emailVerified, LocalDateTime createdAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.emailVerified = emailVerified;
        this.createdAt = createdAt;
    }

    // Getters e Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}