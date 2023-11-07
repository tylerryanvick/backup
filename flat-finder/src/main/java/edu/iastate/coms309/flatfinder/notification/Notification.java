package edu.iastate.coms309.flatfinder.notification;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String type; // e.g., "NEW_MESSAGE", "FRIEND_REQUEST"

    public enum NotificationStatus {
        UNREAD, READ
    }
}