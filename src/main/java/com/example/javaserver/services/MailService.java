package com.example.javaserver.services;

import com.example.javaserver.models.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
@AllArgsConstructor
@PropertySource("classpath:application.properties")
public class MailService {

    private JavaMailSender javaMailSender;
    private Environment env;


    public void sendMyMessage(User user){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessage.setFrom(new InternetAddress(Objects.requireNonNull(env.getProperty("spring.mail.username"))));
        helper.setTo(user.getEmail());
        helper.setText("<h1>Hi! "+ user.getName() + "</h1> <div> your login is-" + user.getName() + "</div>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
