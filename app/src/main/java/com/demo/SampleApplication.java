package com.demo;

import android.util.Log;

import com.demo.base.BootstrapApplication;
import com.demo.base.di.modules.NetworkModule;
import com.demo.data.chat.PreloadedDataProvider;
import com.demo.domain.chat.repository.MessageRepository;
import com.demo.domain.utils.Constants;

/**
 * Created by sahil on 3/17/18.
 */

public class SampleApplication extends BootstrapApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        MessageRepository messageRepository = getAppComponent().provideMessageRepository();
        if(!messageRepository.isPreloadedDataAvailable()){
            messageRepository.insertMessages(PreloadedDataProvider.getDefaultMessages());
            messageRepository.setPreloadedDataAvailable(true);
        }

        Log.d("Sahil", "Data: " + messageRepository.getMessages().toString());
    }

    @Override
    public NetworkModule getNetworkModule() {
        NetworkModule networkModule = new NetworkModule(Constants.BASE_URL);
        return networkModule;
    }
}
