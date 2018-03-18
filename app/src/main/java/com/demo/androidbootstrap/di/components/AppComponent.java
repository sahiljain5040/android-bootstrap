package com.demo.androidbootstrap.di.components;

import com.demo.androidbootstrap.di.modules.AppModule;
import com.demo.androidbootstrap.di.modules.NetworkModule;
import com.castleglobal_clean.ui.activity.SearchActivity;

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
