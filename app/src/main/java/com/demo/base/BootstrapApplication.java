package com.demo.base;

import android.app.Application;
import android.content.Context;

import com.demo.base.di.components.AppComponent;
import com.demo.base.di.components.DaggerAppComponent;
import com.demo.base.di.modules.AppModule;
import com.demo.base.di.modules.NetworkModule;

/**
 * Created by sahil on 10/14/17.
 */

public abstract class BootstrapApplication extends Application {

    //Dependency Injection
    private static AppComponent sAppComponent;
    private static final Object sObjectLock = new Object();
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
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

    public abstract NetworkModule getNetworkModule();
}
