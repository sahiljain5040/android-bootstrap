package com.demo.chat.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.androidbootstrap.R;
import com.demo.base.BaseActivity;
import com.demo.chat.adapter.MessageRecycleAdapter;
import com.demo.chat.presenter.ChatPresenter;
import com.demo.chat.presenter.impl.ChatPresenterImpl;
import com.demo.chat.widget.InputChatView;
import com.demo.domain.chat.models.Message;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends BaseActivity implements ChatPresenter.View, InputChatView.IinputViewStateListener{

    @BindView(R.id.message_list)
    RecyclerView mMessagesListView;

    @BindView(R.id.intervention_container)
    ViewGroup mInterventionContainer;

    @BindView(R.id.input_chat)
    InputChatView mInputChatView;

    @Inject
    ChatPresenterImpl mChatPresenter;
    @Inject
    MessageRecycleAdapter mMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        init();

        mChatPresenter.setView(this);
        try {
            mChatPresenter.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMessagesListView.setLayoutManager(layoutManager);
        mMessagesListView.setAdapter(mMessageAdapter);

        mInputChatView.setInputViewStateListner(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mChatPresenter.resume();
    }

    @Override
    public void onResultsLoading() {

    }

    @Override
    public void onResultsLoaded(List<Message> messages) {
        mMessageAdapter.setMessages(messages);
    }

    @Override
    public void onResultsFailed() {
        Toast.makeText(this, "Sorry, Some Error Occured", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendTextMessage(String message) {
        mChatPresenter.sendMessage(message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mChatPresenter.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mChatPresenter.stop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChatPresenter.destroy();
        mChatPresenter = null;
        mMessageAdapter = null;
    }
}
