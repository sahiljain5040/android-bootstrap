package com.demo.base.di.modules;

import android.app.Activity;
import android.content.Context;

import com.demo.base.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    Context providesContext() {
        return mActivity;
    }
}
