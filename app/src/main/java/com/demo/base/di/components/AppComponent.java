package com.demo.base.di.components;

import android.app.Application;
import android.content.SharedPreferences;

import com.demo.SampleApplication;
import com.demo.base.di.modules.AppModule;
import com.demo.base.di.modules.NetworkModule;
import com.demo.data.chat.daos.MessageDao;
import com.demo.data.chat.database.ChatDatabase;
import com.demo.domain.chat.repository.MessageRepository;
import com.demo.domain.base.executor.Executor;
import com.demo.domain.base.executor.PostExecutionThread;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by sahil on 10/14/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    Application providesApplication();
    SharedPreferences provideSharedPreferences();
    ChatDatabase provideChatDatabase();
    MessageDao provideMessageDao();
    MessageRepository provideMessageRepository();

    void inject(SampleApplication application);

    Executor provideThreadExecutor();
    PostExecutionThread providePostExecutionThread();
    Cache provideOkHttpCache();
    Gson provideGson();
    OkHttpClient provideOkHttpClient();
}
