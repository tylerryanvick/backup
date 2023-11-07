package edu.iastate.coms309.flatfinder.chat.repository;

import edu.iastate.coms309.flatfinder.chat.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    List<ChatRoom> findByRecipientId(String recipientId);
}