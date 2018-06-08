package com.demo.data.chat;

import com.demo.domain.chat.models.Message;

import java.util.UUID;

public class Transformer {

    public static Message getMessageFromDbEntity(com.demo.data.chat.entities.Message message) {
        if (message == null) {
            return null;
        }

        return new Message(message.getUuid(), message.getDirection(), message.getMessage(),
                String.valueOf(message.getTimestamp()));
    }

    public static com.demo.data.chat.entities.Message getDbEntityFromMessage(Message message) {
        if (message == null) {
            return null;
        }
        return new com.demo.data.chat.entities.Message(message.getUuid() == null ?
                UUID.randomUUID().toString() : message.getUuid(), message.getDirection(),
                message.getMessage(), 1);
    }
}
