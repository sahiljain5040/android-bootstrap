package com.demo.base.di.components;

import com.demo.base.di.modules.AppModule;
import com.demo.base.di.modules.NetworkModule;
import com.demo.ui.activity.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sahil on 10/14/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(SearchActivity searchActivity);
}
