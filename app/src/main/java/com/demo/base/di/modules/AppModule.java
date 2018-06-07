package com.demo.base.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.demo.data.chat.daos.MessageDao;
import com.demo.data.chat.database.ChatDatabase;
import com.demo.data.chat.repository.MessageDbRepository;
import com.demo.data.repository.RestaurantApiRepository;
import com.demo.domain.chat.repository.MessageRepository;
import com.demo.domain.executor.Executor;
import com.demo.domain.executor.PostExecutionThread;
import com.demo.domain.executor.impl.ThreadExecutor;
import com.demo.domain.repository.RestaurantRepository;
import com.demo.domain.utils.Constants;
import com.demo.threading.MainThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sahil on 10/14/17.
 */
@Module
public class AppModule {

    Application mApp;

    public AppModule(Application app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mApp);
    }

    @Provides
    @Singleton
    Executor provideThreadExecutor(ThreadExecutor threadExecutor) {
        return threadExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(MainThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    RestaurantRepository provideRestaurantApiRepository(RestaurantApiRepository restaurantApiRepository) {
        return restaurantApiRepository;
    }

    @Provides
    @Singleton
    public ChatDatabase provideChatDatabase(Application application){
        return Room.databaseBuilder(application.getApplicationContext(), ChatDatabase.class,
                Constants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public MessageDao provideMessageDao(ChatDatabase chatDatabase){
        return chatDatabase.getMessageDao();
    }

    @Provides
    @Singleton
    public MessageRepository provideMessageRepository(MessageDbRepository messageDbRepository){
        return messageDbRepository;
    }
}
