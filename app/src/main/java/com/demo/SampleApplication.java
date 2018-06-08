package com.demo;

import com.demo.base.BootstrapApplication;
import com.demo.base.di.modules.NetworkModule;
import com.demo.domain.chat.interactors.PreloadChatUseCase;
import com.demo.domain.base.interactor.UseCaseSubscriber;
import com.demo.domain.chat.repository.MessageRepository;
import com.demo.domain.base.utils.Constants;

import javax.inject.Inject;

/**
 * Created by sahil on 3/17/18.
 */

public class SampleApplication extends BootstrapApplication {

    @Inject
    PreloadChatUseCase mPreloadChatUseCase;
    @Inject
    MessageRepository mMessageRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent().inject(this);
        init();
    }

    private void init(){
        if(!mMessageRepository.isPreloadedDataAvailable()){
            mPreloadChatUseCase.dispose();
            mPreloadChatUseCase.execute(getPreloadDataObserver(), "");
        }
    }

    private UseCaseSubscriber<Boolean> getPreloadDataObserver(){
        return new UseCaseSubscriber<Boolean>(){
            @Override
            public void onNext(Boolean isSuccess) {
                super.onNext(isSuccess);
                if(isSuccess){
                    mMessageRepository.setPreloadedDataAvailable(true);
                }else{
                    mMessageRepository.setPreloadedDataAvailable(false);
                }
            }

            @Override
            public void onError(Throwable exception) {
                super.onError(exception);
                mMessageRepository.setPreloadedDataAvailable(false);
            }
        };
    }

    @Override
    public NetworkModule getNetworkModule() {
        NetworkModule networkModule = new NetworkModule(Constants.BASE_URL);
        return networkModule;
    }
}
