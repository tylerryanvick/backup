package edu.iastate.coms309.flatfinder.chat.model;

import edu.iastate.coms309.flatfinder.users.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "chat_rooms")
public class ChatRoom {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "chat_room_users",
            joinColumns = @JoinColumn(name = "chat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    @Column(nullable = false)
    private String name;

    public ChatRoom() {
        // Default constructor needed by JPA
    }

    // Constructor, Getters, and Setters are generated by Lombok
}