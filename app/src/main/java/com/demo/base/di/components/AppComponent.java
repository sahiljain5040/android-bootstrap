package com.demo.base.di.components;

import android.app.Application;
import android.content.SharedPreferences;

import com.demo.base.di.modules.AppModule;
import com.demo.base.di.modules.NetworkModule;
import com.demo.domain.executor.Executor;
import com.demo.domain.executor.PostExecutionThread;
import com.demo.domain.repository.RestaurantRepository;
import com.demo.ui.activity.SearchActivity;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by sahil on 10/14/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(SearchActivity searchActivity);

    Retrofit provideRetrofit();

    Application providesApplication();

    SharedPreferences provideSharedPreferences();

    Executor provideThreadExecutor();

    PostExecutionThread providePostExecutionThread();

    RestaurantRepository provideRestaurantApiRepository();

    Cache provideOkHttpCache();
    Gson provideGson();
    OkHttpClient provideOkHttpClient();
}
