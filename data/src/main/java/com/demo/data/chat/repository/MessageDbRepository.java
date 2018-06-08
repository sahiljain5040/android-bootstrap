package com.demo.data.chat.repository;

import android.app.Application;
import android.content.SharedPreferences;

import com.demo.data.R;
import com.demo.data.chat.Transformer;
import com.demo.data.chat.daos.MessageDao;
import com.demo.data.chat.response.ChatApiResponse;
import com.demo.domain.chat.models.Message;
import com.demo.domain.chat.repository.MessageRepository;
import com.demo.domain.utils.Constants;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

@Singleton
public class MessageDbRepository implements MessageRepository{

    private Application application;
    private MessageDao messageDao;
    private SharedPreferences sharedPreferences;

    @Inject
    public MessageDbRepository(Application app, MessageDao messageDao, SharedPreferences sharedPreferences){
        this.application = app;
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

    @Override
    public void loadMessages() throws Exception {
        InputStream is = application.getResources().openRawResource(R.raw.chat);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        }finally {
            is.close();
        }

        String jsonString = writer.toString();
        ChatApiResponse chatApiResponse = new Gson().fromJson(jsonString, ChatApiResponse.class);
        insertMessages(chatApiResponse.getChat());
    }
}
