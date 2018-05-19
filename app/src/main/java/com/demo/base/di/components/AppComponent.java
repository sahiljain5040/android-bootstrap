package com.demo.base.di.components;

import android.app.Application;
import android.content.SharedPreferences;

import com.demo.base.di.modules.AppModule;
import com.demo.base.di.modules.NetworkModule;
import com.demo.data.network.api.SearchApi;
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

    Executor provideThreadExecutor();
    PostExecutionThread providePostExecutionThread();
    RestaurantRepository provideRestaurantApiRepository();
    Cache provideOkHttpCache();
    Gson provideGson();
    OkHttpClient provideOkHttpClient();
    SearchApi provideSearchApi();
}
