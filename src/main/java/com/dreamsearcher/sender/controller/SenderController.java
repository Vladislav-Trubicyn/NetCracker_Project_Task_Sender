package com.dreamsearcher.sender.controller;

import com.dreamsearcher.sender.model.Notification;
import com.dreamsearcher.sender.service.SenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sender")
public class SenderController
{
    private SenderService senderService;

    public SenderController(SenderService senderService)
    {
        this.senderService = senderService;
    }

    @PostMapping
    public void sendNotification(@RequestBody List<Notification> notifications)
    {
        senderService.sendNotification(notifications);
    }
}
