package com.etmg.user.service;

import com.etmg.user.dto.LoginRequest;
import com.etmg.user.dto.LoginResponse;
import com.etmg.user.dto.ProfileResponse;
import com.etmg.user.dto.RegisterRequest;
import com.etmg.user.dto.RegisterResponse;
import com.etmg.user.model.User;
import com.etmg.user.repository.UserRepository;
import com.etmg.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Regex para validar email
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    // Regex para validar senha (min 12 chars, 1 maiúscula, 1 especial)
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{12,}$";

    public RegisterResponse registerUser(RegisterRequest request) {

        // 1. Validar se campos não estão vazios
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }

        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }

        // 2. Validar formato do email
        if (!Pattern.matches(EMAIL_REGEX, request.getEmail())) {
            throw new IllegalArgumentException("Email inválido");
        }

        // 3. Verificar se email já existe
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        // 4. Validar senha (12+ chars, maiúscula, especial)
        if (!Pattern.matches(PASSWORD_REGEX, request.getPassword())) {
            throw new IllegalArgumentException(
                    "Senha deve ter no mínimo 12 caracteres, 1 letra maiúscula e 1 caractere especial");
        }

        // 5. Criptografar senha
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // 6. Criar usuário
        User user = new User(
                request.getName(),
                request.getEmail(),
                hashedPassword);

        // 7. Salvar no banco
        User savedUser = userRepository.save(user);

        // TODO: Enviar email de confirmação aqui (próxima etapa)

        // 8. Retornar resposta
        return new RegisterResponse(
                savedUser.getId(),
                "Usuário criado! Verifique seu email para confirmar.");
    }

    public LoginResponse loginUser(LoginRequest request) {

        // 1. Validar se campos não estão vazios
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }

        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }

        // 2. Buscar usuário por email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        // 3. Verificar se a senha está correta
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        // 4. Gerar token JWT
        String token = jwtUtil.generateToken(user.getId(), user.getEmail());

        // 5. Retornar resposta
        return new LoginResponse(
                token,
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    public ProfileResponse getProfile(Long userId) {

        // Busca usuário por ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Retorna perfil
        return new ProfileResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getEmailVerified(),
                user.getCreatedAt());
    }
}