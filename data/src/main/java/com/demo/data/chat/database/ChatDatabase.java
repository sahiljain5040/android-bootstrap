package com.demo.data.chat.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.demo.data.chat.daos.MessageDao;
import com.demo.data.chat.entities.Message;
import com.demo.domain.base.utils.Constants;

import javax.inject.Singleton;

@Singleton
@Database(entities = {Message.class}, version = Constants.DATABASE_VERSION)
public abstract class ChatDatabase extends RoomDatabase{

    public abstract MessageDao getMessageDao();
}
