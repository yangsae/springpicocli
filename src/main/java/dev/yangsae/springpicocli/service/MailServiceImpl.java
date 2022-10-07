package dev.yangsae.springpicocli.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MainService")
public class MailServiceImpl implements IMailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
    private static final String NOREPLY_ADDRESS = "noreply@dev.yangsae";

    private final JavaMailSender emailSender;

    public MailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendMessage(List<String> to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(NOREPLY_ADDRESS);

        for (String recipient : to) {
            message.setTo(recipient);
        }

        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
        LOGGER.info("Mail to {} sent! Subject: {}, Body: {}", to, subject, body);
    }
}
