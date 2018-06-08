package com.demo.domain.chat.repository;

import com.demo.domain.chat.models.Message;

import java.util.List;

import io.reactivex.Flowable;

public interface MessageRepository {

    Flowable<List<Message>> getMessagesObservable();

    List<Message> getMessages();

    boolean isPreloadedDataAvailable();

    void setPreloadedDataAvailable(boolean isAvailable);

    void insertMessage(Message message);

    void insertMessages(List<Message> messages);

    void loadMessages() throws Exception;
}
