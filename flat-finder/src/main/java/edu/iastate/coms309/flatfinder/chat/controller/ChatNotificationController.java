package edu.iastate.coms309.flatfinder.chat.controller;

import edu.iastate.coms309.flatfinder.chat.model.ChatNotification;
import edu.iastate.coms309.flatfinder.chat.service.ChatNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatNotificationController {

    private final ChatNotificationService chatNotificationService;

    @MessageMapping("/notification.create")
    @SendTo("/topic/notifications")
    public ChatNotification createNotification(ChatNotification notification) {
        return chatNotificationService.saveNotification(notification);
    }

    @MessageMapping("/notification.get")
    @SendTo("/topic/notifications")
    public ChatNotification getNotification(String notificationId) {
        return chatNotificationService.findNotificationById(notificationId);
    }

    @MessageMapping("/notification.getAllForUser")
    @SendTo("/topic/notifications")
    public List<ChatNotification> getNotificationsForUser(String userId) {
        return chatNotificationService.findNotificationsForUser(userId);
    }

    @MessageMapping("/notification.updateStatus")
    @SendTo("/topic/notifications")
    public ChatNotification updateNotificationStatus(String notificationId, ChatNotification.NotificationStatus status) {
        return chatNotificationService.updateNotificationStatus(notificationId, status);
    }

    @MessageMapping("/notification.delete")
    @SendTo("/topic/notifications")
    public void deleteNotification(String notificationId) {
        chatNotificationService.deleteNotification(notificationId);
    }
}