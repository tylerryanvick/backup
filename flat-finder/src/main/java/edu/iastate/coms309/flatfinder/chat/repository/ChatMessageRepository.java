package edu.iastate.coms309.flatfinder.chat.repository;

import edu.iastate.coms309.flatfinder.chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findByChatRoomId(Integer chatRoomId);
}