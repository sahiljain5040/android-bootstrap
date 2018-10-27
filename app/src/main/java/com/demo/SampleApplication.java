package com.demo;

import com.demo.base.BootstrapApplication;
import com.demo.base.di_new.modules.NetworkModule;
import com.demo.domain.base.Constants;

/**
 * Created by sahil on 3/17/18.
 */

public class SampleApplication extends BootstrapApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public NetworkModule getNetworkModule() {
        NetworkModule networkModule = new NetworkModule(Constants.INSTANCE.getBASE_URL());
        return networkModule;
    }
}
