package com.api.travelsisters.chat;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ChatRoomManager {
    private Map<String, Set<String>> chatUserMap = new HashMap<>();

    public void createRoom(String roomName) {
        chatUserMap.put(roomName, new HashSet<>());
    }

    public void addUserToChat(String chatName, String userName) {
        if (!chatUserMap.containsKey(chatName)) {
            chatUserMap.put(chatName, new HashSet<>());
        }

        Set<String> usersInRoom = chatUserMap.get(chatName);
        usersInRoom.add(userName);
    }

    public void removeUserFromChat(String roomName, String userName) {
        chatUserMap.getOrDefault(roomName, new HashSet<>()).remove(userName);
    }

    public boolean isUserInChat(String chatName, String userName) {
        Set<String> usersInRoom = chatUserMap.get(chatName);
        if (usersInRoom != null) {
            return usersInRoom.contains(userName);
        }
        return false;
    }

    public Set<String> getUsersInChat(String roomName) {
        return chatUserMap.getOrDefault(roomName, new HashSet<>());
    }
}
