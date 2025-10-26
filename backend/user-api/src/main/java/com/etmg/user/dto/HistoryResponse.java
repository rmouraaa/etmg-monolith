package com.etmg.user.dto;

import java.time.LocalDateTime;
import java.util.List;

public class HistoryResponse {

    private List<QuestionItem> questions;
    private int currentPage;
    private int totalPages;
    private long totalQuestions;

    // Construtor vazio
    public HistoryResponse() {
    }

    // Construtor com parâmetros
    public HistoryResponse(List<QuestionItem> questions, int currentPage, int totalPages, long totalQuestions) {
        this.questions = questions;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalQuestions = totalQuestions;
    }

    // Getters e Setters
    public List<QuestionItem> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionItem> questions) {
        this.questions = questions;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(long totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    // Classe interna para cada pergunta
    public static class QuestionItem {
        private Long questionId;
        private String question;
        private LocalDateTime date;

        public QuestionItem() {
        }

        public QuestionItem(Long questionId, String question, LocalDateTime date) {
            this.questionId = questionId;
            this.question = question;
            this.date = date;
        }

        // Getters e Setters
        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }
    }
}