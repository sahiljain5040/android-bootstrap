package com.demo.chat.presenter.impl;

import com.demo.chat.presenter.ChatPresenter;
import com.demo.domain.chat.interactors.GetMessagesUseCase;
import com.demo.domain.chat.interactors.SendMessageUseCase;
import com.demo.domain.chat.interactors.UseCaseSubscriber;
import com.demo.domain.chat.models.Message;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import static com.demo.domain.utils.Constants.INTERVENTION_DELAY;

public class ChatPresenterImpl implements ChatPresenter {

    private GetMessagesUseCase mGetMessagesUseCase;
    private SendMessageUseCase mSendMessageUseCase;
    private ChatPresenter.View mView;
    private Timer mTimer;

    @Inject
    public ChatPresenterImpl(GetMessagesUseCase getMessagesUseCase, SendMessageUseCase sendMessageUseCase){
        mGetMessagesUseCase = getMessagesUseCase;
        mSendMessageUseCase = sendMessageUseCase;
    }

    @Override
    public void setView(View view) {
        mView = view;
    }

    @Override
    public void load() throws Exception {

        if(mView != null){
            mView.onResultsLoading();
        }else{
            throw new Exception("setView() not called Before calling load()");
        }

        mGetMessagesUseCase.dispose();
        mGetMessagesUseCase.execute(getMessageListObserver(), "");
    }

    @Override
    public void sendMessage(String message) {
        mSendMessageUseCase.dispose();
        mSendMessageUseCase.execute(getSendMessageObserver(), message);
    }

    private UseCaseSubscriber<List<Message>> getMessageListObserver(){

        return new UseCaseSubscriber<List<Message>>(){
            @Override
            public void onNext(List<Message> messages) {
                super.onNext(messages);
                mView.onResultsLoaded(messages);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
                mView.onResultsFailed();
            }
        };
    }

    private UseCaseSubscriber<Boolean> getSendMessageObserver(){

        return new UseCaseSubscriber<Boolean>(){
            @Override
            public void onNext(Boolean isSuccess) {
                super.onNext(isSuccess);
                cancelInterventionTimer();
                startInterventionTimer();
                mView.hideIntervention();
                mSendMessageUseCase.dispose();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
            }
        };
    }

    @Override
    public void resume() {
        startInterventionTimer();
    }

    private void startInterventionTimer(){
        TimerTask timerTask;
        if(mTimer == null){
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    mView.showIntervention();
                }
            };
            mTimer = new Timer();
            mTimer.schedule(timerTask, INTERVENTION_DELAY);//delay of 1 min
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {
        cancelInterventionTimer();
    }

    private void cancelInterventionTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void destroy() {
        mView = null;
        mGetMessagesUseCase.dispose();
        mSendMessageUseCase.dispose();
    }
}
