package com.scheduler.api.infra.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.properties.mail.smtp.mail}")
    String from;

    public boolean sendMail(String to, String subject, String content) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress(from));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(content, "utf-8");
            }
        };

        try {
            javaMailSender.send(preparator);
            return true;
        } catch (MailException e) {
            log.error("MailException", e);
            return false;
        }
    }
}
