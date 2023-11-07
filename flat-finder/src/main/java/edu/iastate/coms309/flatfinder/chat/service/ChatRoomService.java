package edu.iastate.coms309.flatfinder.chat.service;

import edu.iastate.coms309.flatfinder.chat.model.ChatRoom;
import edu.iastate.coms309.flatfinder.chat.repository.ChatRoomRepository;
import edu.iastate.coms309.flatfinder.users.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom saveChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public Optional<ChatRoom> findChatRoomById(String id) {
        return chatRoomRepository.findById(id);
    }

    public List<ChatRoom> findAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public void deleteChatRoom(String id) {
        chatRoomRepository.deleteById(id);
    }

    public ChatRoom updateChatRoomName(String id, String name) {
        ChatRoom chatRoom = findChatRoomById(id)
                .orElseThrow(() -> new IllegalStateException("Chat room not found with id: " + id));
        chatRoom.setName(name);
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom updateChatRoomParticipants(String id, Set<User> participants) {
        ChatRoom chatRoom = findChatRoomById(id)
                .orElseThrow(() -> new IllegalStateException("Chat room not found with id: " + id));
        chatRoom.setParticipants(participants);
        return chatRoomRepository.save(chatRoom);
    }
}