package com.etmg.user.repository;

import com.etmg.user.model.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    // Busca código por userId e código (ainda não verificado)
    Optional<EmailVerification> findByUserIdAndCodeAndVerifiedFalse(Long userId, String code);

    // Busca último código enviado para o usuário
    Optional<EmailVerification> findTopByUserIdOrderByCreatedAtDesc(Long userId);

    // Deleta códigos antigos do usuário (cleanup)
    void deleteByUserId(Long userId);
}