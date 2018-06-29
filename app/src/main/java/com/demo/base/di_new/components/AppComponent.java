package com.demo.base.di_new.components;

import com.demo.base.BootstrapApplication;
import com.demo.base.di_new.modules.ActivityProvider;
import com.demo.base.di_new.modules.AppModule;
import com.demo.base.di_new.modules.FragmentProvider;
import com.demo.base.di_new.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by sahil on 10/14/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class,
        AndroidInjectionModule.class, ActivityProvider.class, FragmentProvider.class})
public interface AppComponent {
    void inject(BootstrapApplication application);
}
