package com.api.travelsisters.controller;

import com.api.travelsisters.chat.ChatMessage;
import com.api.travelsisters.chat.ChatRoomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/chat-service")
public class ChatController {
    private final ChatRoomManager chatManager;

    public void teste() {
        chatManager.createRoom("teste");
        chatManager.addUserToChat("teste","carlos");
        chatManager.createRoom("teste2");
        chatManager.addUserToChat("teste2","eduardo");
    }

    @Autowired
    public ChatController(ChatRoomManager chatManager) {
        this.chatManager = chatManager;
        teste();
    }

    @MessageMapping("/chat/{chatName}")
    @SendTo("/topic/{chatName}")
    public ChatMessage sendMessage(@DestinationVariable String chatName,
//                                   @PathVariable String userName,
                                   ChatMessage message) {
        //Verifica se o usuario está no chat
//        if (!chatManager.isUserInChat(chatName, userName)) {
//            return null;
//        }
        return message;
    }

    public void addUserToChat(String chatName, String userName) {
        chatManager.addUserToChat(chatName, userName);
    }

    public void removeUserFromChat(String chatName, String userName) {
        chatManager.removeUserFromChat(chatName, userName);
    }

    public Set<String> getUsersInRoom(String chatName) {
        return chatManager.getUsersInChat(chatName);
    }
}