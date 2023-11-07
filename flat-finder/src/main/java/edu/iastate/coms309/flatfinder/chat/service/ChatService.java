package edu.iastate.coms309.flatfinder.chat.service;

import edu.iastate.coms309.flatfinder.chat.model.ChatMessage;
import edu.iastate.coms309.flatfinder.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage saveChatMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public Optional<ChatMessage> findChatMessageById(String id) {
        return chatMessageRepository.findById(id);
    }

    public List<ChatMessage> findChatMessagesByChatRoomId(String chatRoomId) {
        return chatMessageRepository.findByChatRoomId(chatRoomId);
    }

    public void deleteChatMessage(String id) {
        chatMessageRepository.deleteById(id);
    }

    public ChatMessage updateChatMessageContent(String id, String content) {
        ChatMessage chatMessage = findChatMessageById(id)
                .orElseThrow(() -> new IllegalStateException("Chat message not found with id: " + id));
        chatMessage.setContent(content);
        return chatMessageRepository.save(chatMessage);
    }

    public ChatMessage updateChatMessageStatus(String id, ChatMessage.MessageStatus status) {
        ChatMessage chatMessage = findChatMessageById(id)
                .orElseThrow(() -> new IllegalStateException("Chat message not found with id: " + id));
        chatMessage.setStatus(status);
        return chatMessageRepository.save(chatMessage);
    }
}