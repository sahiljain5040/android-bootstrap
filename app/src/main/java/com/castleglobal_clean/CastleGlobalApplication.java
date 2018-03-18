package com.castleglobal_clean;

import com.castleglobal_clean.domain.utils.Constants;
import com.demo.androidbootstrap.BootstrapApplication;
import com.demo.androidbootstrap.di.modules.NetworkModule;

/**
 * Created by sahil on 3/17/18.
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
