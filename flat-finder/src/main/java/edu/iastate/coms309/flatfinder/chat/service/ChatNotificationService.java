package edu.iastate.coms309.flatfinder.chat.service;

import edu.iastate.coms309.flatfinder.chat.model.ChatNotification;
import edu.iastate.coms309.flatfinder.chat.repository.ChatNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatNotificationService {

    private final ChatNotificationRepository chatNotificationRepository;

    public ChatNotification saveNotification(ChatNotification notification) {
        return chatNotificationRepository.save(notification);
    }

    public List<ChatNotification> findAllNotifications() {
        return chatNotificationRepository.findAll();
    }

    public ChatNotification findNotificationById(String id) {
        return chatNotificationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Notification not found with id: " + id));
    }

    public void deleteNotification(String id) {
        chatNotificationRepository.deleteById(id);
    }

    public ChatNotification updateNotificationStatus(String id, ChatNotification.NotificationStatus status) {
        ChatNotification notification = findNotificationById(id);
        notification.setStatus(status);
        return chatNotificationRepository.save(notification);
    }

    public List<ChatNotification> findNotificationsForUser(String userId) {
        return chatNotificationRepository.findByRecipientId(userId);
    }
}