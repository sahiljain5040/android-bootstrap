package com.demo.data.chat.repository;

import android.content.SharedPreferences;

import com.demo.data.chat.Transformer;
import com.demo.data.chat.daos.MessageDao;
import com.demo.domain.chat.models.Message;
import com.demo.domain.chat.repository.MessageRepository;
import com.demo.domain.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

@Singleton
public class MessageDbRepository implements MessageRepository{

    private MessageDao messageDao;
    private SharedPreferences sharedPreferences;

    @Inject
    public MessageDbRepository(MessageDao messageDao, SharedPreferences sharedPreferences){
        this.messageDao = messageDao;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Flowable<List<Message>> getMessagesObservable() {
        return messageDao.getMessagesObservable()
                .map(new Function<List<com.demo.data.chat.entities.Message>, List<Message>>() {
            @Override
            public List<Message> apply(List<com.demo.data.chat.entities.Message> messages) throws Exception {
                List<Message> messageList = new ArrayList<>();
                for(com.demo.data.chat.entities.Message message: messages){
                    messageList.add(Transformer.getMessageFromDbEntity(message));
                }
                return messageList;
            }
        });
    }

    @Override
    public List<Message> getMessages() {
        List<com.demo.data.chat.entities.Message> messageList = messageDao.getMessages();
        List<Message> messages = new ArrayList<>();
        for(com.demo.data.chat.entities.Message message: messageList){
            messages.add(Transformer.getMessageFromDbEntity(message));
        }
        return messages;
    }

    @Override
    public boolean isPreloadedDataAvailable() {
        return sharedPreferences.getBoolean(Constants.PRELOADED_DATA, false);
    }

    @Override
    public void setPreloadedDataAvailable(boolean isAvailable) {
        sharedPreferences.edit().putBoolean(Constants.PRELOADED_DATA, isAvailable).apply();
    }

    @Override
    public void insertMessage(Message message) {
        messageDao.insertMessage(Transformer.getDbEntityFromMessage(message));
    }

    @Override
    public void insertMessages(List<Message> messages) {
        List<com.demo.data.chat.entities.Message> messageList = new ArrayList<>();
        for(Message message: messages){
            messageList.add(Transformer.getDbEntityFromMessage(message));
        }
        messageDao.insertMessages(messageList);
    }
}
