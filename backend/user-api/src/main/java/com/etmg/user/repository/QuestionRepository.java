package com.etmg.user.repository;

import com.etmg.user.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // Busca perguntas de um usuário com paginação
    Page<Question> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    // Conta quantas perguntas o usuário fez
    long countByUserId(Long userId);

    // Deleta todas as perguntas de um usuário (usado no delete account)
    void deleteByUserId(Long userId);
}