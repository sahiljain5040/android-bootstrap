package com.demo.base.di_new.modules;

import com.demo.search.fragment.TestFragment;
import com.demo.search.module.TestFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentProvider {

    @ContributesAndroidInjector(modules = TestFragmentModule.class)
    abstract TestFragment bindTestFragment();
}
