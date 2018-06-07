package com.demo.chat.presenter;

import com.demo.domain.chat.models.Message;
import com.demo.presenters.base.BasePresenter;

import java.util.List;

public interface ChatPresenter extends BasePresenter{

    interface View{
        void onResultsLoading();
        void onResultsLoaded(List<Message> messages);
        void onResultsFailed();
    }

    void setView(ChatPresenter.View view);
    void load() throws Exception;
    void sendMessage(String message);
}
