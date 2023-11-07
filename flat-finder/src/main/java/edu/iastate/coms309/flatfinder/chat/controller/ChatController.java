package edu.iastate.coms309.flatfinder.chat.controller;

import edu.iastate.coms309.flatfinder.chat.model.ChatMessage;
import edu.iastate.coms309.flatfinder.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatService.saveChatMessage(chatMessage);
    }

    @MessageMapping("/chat.updateMessage")
    @SendTo("/topic/public")
    public ChatMessage updateMessage(ChatMessage chatMessage) {
        return chatService.updateChatMessageContent(chatMessage.getId(), chatMessage.getContent());
    }

    @MessageMapping("/chat.updateMessageStatus")
    @SendTo("/topic/public")
    public ChatMessage updateMessageStatus(ChatMessage chatMessage) {
        return chatService.updateChatMessageStatus(chatMessage.getId(), chatMessage.getStatus());
    }

    @MessageMapping("/chat.deleteMessage")
    @SendTo("/topic/public")
    public void deleteMessage(ChatMessage chatMessage) {
        chatService.deleteChatMessage(chatMessage.getId());
    }
}