package com.demo.base.di.modules;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.demo.base.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    Fragment providesFragment() {
        return mFragment;
    }

    @Provides
    @PerFragment
    Activity provideActivity() {
        return mFragment.getActivity();
    }

}