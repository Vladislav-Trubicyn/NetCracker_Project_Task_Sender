package com.dreamsearcher.sender.service;

import com.dreamsearcher.sender.error.EmailSendTroubleException;
import com.dreamsearcher.sender.model.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SenderService
{
    @Value("${spring.mail.username}")
    private String emailSender;
    @Value("${notifier.service.host}")
    private String SERVICE_HOST;
    @Value("${notifier.service.port}")
    private String SERVICE_PORT;
    @Value("${notifier.service.path}")
    private String SERVICE_PATH;

    private SimpleMailMessage simpleMailMessage;
    private JavaMailSender javaMailSender;
    private RestTemplate restTemplate;


    public SenderService(SimpleMailMessage simpleMailMessage, JavaMailSender javaMailSender, RestTemplate restTemplate)
    {
        this.simpleMailMessage = simpleMailMessage;
        this.javaMailSender = javaMailSender;
        this.restTemplate = restTemplate;
    }

    public void sendNotification(List<Notification> notifications)
    {
        notifications.stream().forEach(notification -> {

            simpleMailMessage.setTo(notification.getUserEmail());
            simpleMailMessage.setFrom(emailSender);
            simpleMailMessage.setSubject("Уважаемый пользователь, "  + notification.getUsername());
            simpleMailMessage.setText(notification.getNotificationText());

            try
            {
                javaMailSender.send(simpleMailMessage);
                notification.setSend(true);
            }
            catch (MailException mailException)
            {
                throw new EmailSendTroubleException(mailException.getMessage());
            }

        });

        restTemplate.postForObject(SERVICE_HOST + ":" + SERVICE_PORT + SERVICE_PATH, notifications, List.class);
    }
}
