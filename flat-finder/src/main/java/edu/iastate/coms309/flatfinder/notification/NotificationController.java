package edu.iastate.coms309.flatfinder.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @MessageMapping("/notifications/create")
    @SendTo("/user/{userId}/queue/notifications")
    public Notification createNotification(Notification notification) {
        return notificationService.createNotification(notification);
    }

    @MessageMapping("/notifications/update")
    @SendTo("/user/{userId}/queue/notifications")
    public Notification updateNotification(Notification notification) {
        return notificationService.updateNotification(notification);
    }

    @MessageMapping("/notifications/delete")
    @SendTo("/user/{userId}/queue/notifications")
    public void deleteNotification(String notificationId) {
        notificationService.deleteNotification(notificationId);
    }

    @MessageMapping("/notifications/getAllForUser")
    @SendTo("/user/{userId}/queue/notifications")
    public List<Notification> getAllNotificationsForUser(String userId) {
        return notificationService.getAllNotificationsForUser(userId);
    }
}