package com.demo.data.chat.response;

import com.demo.domain.chat.models.Message;

import java.util.List;

public class ChatApiResponse {

    private List<Message> chat;

    public List<Message> getChat() {
        return chat;
    }

    public void setChat(List<Message> chat) {
        this.chat = chat;
    }
}
