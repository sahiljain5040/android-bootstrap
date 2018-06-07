package com.demo.data.chat.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.demo.data.chat.entities.Message;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM Message")
    Flowable<List<Message>> getMessagesObservable();

    @Query("SELECT * FROM Message")
    List<Message> getMessages();

    @Insert
    void insertMessage(Message message);

    @Insert
    void insertMessages(List<Message> messages);
}
