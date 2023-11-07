package edu.iastate.coms309.flatfinder.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now()); // Set the current time when creating the notification
        return notificationRepository.save(notification);
    }

    public Notification getNotificationById(String id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found with id: " + id));
    }

    public List<Notification> getAllNotificationsForUser(String userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    public Notification updateNotification(Notification updatedNotification) {
        Notification existingNotification = getNotificationById(updatedNotification.getId());
        existingNotification.setContent(updatedNotification.getContent());
        existingNotification.setStatus(updatedNotification.getStatus());
        existingNotification.setType(updatedNotification.getType());
        return notificationRepository.save(existingNotification);
    }

    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }
}