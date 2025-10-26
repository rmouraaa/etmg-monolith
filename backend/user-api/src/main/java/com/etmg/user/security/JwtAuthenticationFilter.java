package com.etmg.user.security;

import com.etmg.user.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 1. Pega o header Authorization
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        // 2. Extrai o token (formato: "Bearer eyJhbGc...")
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                email = jwtUtil.getEmailFromToken(token);
            } catch (Exception e) {
                // Token inválido
            }
        }

        // 3. Valida o token e autentica o usuário
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null,
                        new ArrayList<>());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Coloca o userId como atributo da requisição (útil depois)
                request.setAttribute("userId", jwtUtil.getUserIdFromToken(token));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 4. Continua o fluxo
        filterChain.doFilter(request, response);
    }
}