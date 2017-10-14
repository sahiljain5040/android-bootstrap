package com.demo.androidbootstrap.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
}
