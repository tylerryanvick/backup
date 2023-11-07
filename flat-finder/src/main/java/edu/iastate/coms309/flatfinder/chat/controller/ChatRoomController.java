package edu.iastate.coms309.flatfinder.chat.controller;

import edu.iastate.coms309.flatfinder.chat.model.ChatRoom;
import edu.iastate.coms309.flatfinder.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @MessageMapping("/room.create")
    @SendTo("/topic/rooms")
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomService.saveChatRoom(chatRoom);
    }

    @MessageMapping("/room.update")
    @SendTo("/topic/rooms")
    public ChatRoom updateChatRoom(ChatRoom chatRoom) {
        return chatRoomService.saveChatRoom(chatRoom);
    }

    @MessageMapping("/room.delete")
    @SendTo("/topic/rooms")
    public void deleteChatRoom(String roomId) {
        chatRoomService.deleteChatRoom(roomId);
    }

    @MessageMapping("/room.get")
    @SendTo("/topic/rooms")
    public ChatRoom getChatRoom(String roomId) {
        return chatRoomService.findChatRoomById(roomId)
                .orElseThrow(() -> new IllegalStateException("Chat room not found with id: " + roomId));
    }

    @MessageMapping("/room.getAll")
    @SendTo("/topic/rooms")
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.findAllChatRooms();
    }
}