package com.demo.base.di_new.modules;

import com.demo.search.activity.SearchActivity;
import com.demo.search.module.SearchActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityProvider {

    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity bindNewSearchActivity();

}
