package com.project.castleglobal;

import com.demo.androidbootstrap.BootstrapApplication;
import com.demo.androidbootstrap.di.modules.NetworkModule;
import com.project.castleglobal.utils.Constants;

/**
 * Created by sahil on 10/15/17.
 */

public class CastleGlobalApplication extends BootstrapApplication{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public NetworkModule getNetworkModule() {
        NetworkModule networkModule = new NetworkModule(Constants.BASE_URL);
        return networkModule;
    }
}
