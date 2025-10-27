package com.etmg.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendVerificationCode(String toEmail, String code, String userName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject("Confirme seu email - Explain To Me Genius");
            message.setText(buildEmailBody(userName, code));

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email: " + e.getMessage());
        }
    }

    private String buildEmailBody(String userName, String code) {
        return String.format(
                "Olá, %s!\n\n" +
                        "Obrigado por se cadastrar no Explain To Me Genius!\n\n" +
                        "Seu código de verificação é: %s\n\n" +
                        "Este código expira em 15 minutos.\n\n" +
                        "Se você não se cadastrou, ignore este email.\n\n" +
                        "Atenciosamente,\n" +
                        "Equipe Explain To Me Genius",
                userName, code);
    }
}