package com.demo.androidbootstrap;

import android.app.Application;
import android.content.Context;

import com.demo.androidbootstrap.di.components.AppComponent;
import com.demo.androidbootstrap.di.components.DaggerAppComponent;
import com.demo.androidbootstrap.di.modules.AppModule;
import com.demo.androidbootstrap.di.modules.NetworkModule;

/**
 * Created by sahil on 10/14/17.
 */

public class BootstrapApplication extends Application {

    //Dependency Injection
    private static AppComponent sAppComponent;
    private static Object sObjectLock = new Object();
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }

    public static AppComponent getAppComponent(Context context) {
        BootstrapApplication application = (BootstrapApplication) context.getApplicationContext();
        if (sAppComponent == null)
            synchronized (sObjectLock) {
                if (sAppComponent == null) {
                    sAppComponent = DaggerAppComponent.
                            builder()
                            .appModule(application.getApplicationModule())
                            .networkModule(application.getNetworkModule())
                            .build();
                }
            }
        return sAppComponent;
    }

    private AppModule getApplicationModule() {
        return new AppModule(this);
    }

    public NetworkModule getNetworkModule() {
        NetworkModule networkModule = new NetworkModule("https://developers.zomato.com/");
        return networkModule;
    }
}
