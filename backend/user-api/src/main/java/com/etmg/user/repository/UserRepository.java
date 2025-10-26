package com.etmg.user.repository;

import com.etmg.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Busca usuário por email
    Optional<User> findByEmail(String email);

    // Verifica se email já existe
    boolean existsByEmail(String email);
}