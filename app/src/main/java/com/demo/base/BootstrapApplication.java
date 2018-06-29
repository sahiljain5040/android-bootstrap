package com.demo.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.demo.base.di_new.components.AppComponent;
import com.demo.base.di_new.components.DaggerAppComponent;
import com.demo.base.di_new.modules.AppModule;
import com.demo.base.di_new.modules.NetworkModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by sahil on 10/14/17.
 */

public abstract class BootstrapApplication extends Application implements HasActivityInjector{

    //Dependency Injection
    private static AppComponent sAppComponent;
    private static final Object sObjectLock = new Object();
    private static Context sContext;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        getAppComponent().inject(this);
    }

    public static Context getContext() {
        return sContext;
    }

    public AppComponent getAppComponent() {
        if (sAppComponent == null)
            synchronized (sObjectLock) {
                if (sAppComponent == null) {
                    sAppComponent = DaggerAppComponent.
                            builder()
                            .appModule(getApplicationModule())
                            .networkModule(getNetworkModule())
                            .build();
                }
            }
        return sAppComponent;
    }

    private AppModule getApplicationModule() {
        return new AppModule(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    public abstract NetworkModule getNetworkModule();
}
