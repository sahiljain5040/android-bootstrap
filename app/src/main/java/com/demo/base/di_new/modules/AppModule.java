package com.demo.base.di_new.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.demo.data.search.repository.RestaurantApiRepository;
import com.demo.domain.base.executor.Executor;
import com.demo.domain.base.executor.PostExecutionThread;
import com.demo.domain.base.executor.impl.ThreadExecutor;
import com.demo.domain.search.repository.RestaurantRepository;
import com.demo.base.threading.MainThread;

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
}
