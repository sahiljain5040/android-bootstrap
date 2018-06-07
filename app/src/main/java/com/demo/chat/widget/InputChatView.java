package com.demo.chat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.demo.androidbootstrap.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InputChatView extends RelativeLayout{

    @BindView(R.id.send_message)
    View sendMessageContainer;
    @BindView(R.id.edit_message)
    EditText edtMessage;
    @BindView(R.id.send_msg_btn)
    ImageButton sendMsgBtn;

    private IinputViewStateListener inputViewStateListener;

    public InputChatView(Context context) {
        super(context);
        initialize();
    }

    public InputChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public InputChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        getLayoutInflater().inflate(R.layout.view_input_chat, this);

        ButterKnife.bind(this);
        edtMessage.setSingleLine(false);
        edtMessage.setMaxLines(6);

        EditText.KeyboardListener listener = new EditText.KeyboardListener() {

            @Override
            public void onTextChanged(int length) {
                if(length > 0){
                    sendMsgBtn.setImageDrawable(getResources().getDrawable(R.mipmap.ic_send_active));
                }else{
                    sendMsgBtn.setImageDrawable(getResources().getDrawable(R.mipmap.ic_send_inactive));
                }
            }
        };

        edtMessage.setKeyboardListener(listener);

        sendMsgBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputViewStateListener != null && edtMessage.getText().length() > 0){
                    inputViewStateListener.sendTextMessage(edtMessage.getText().toString());
                    edtMessage.clear();
                }
            }
        });
    }

    public void setText(String text) {
        edtMessage.setText(text);
    }

    public void setInputViewStateListner(IinputViewStateListener listner) {
        this.inputViewStateListener = listner;
    }

    private LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public interface IinputViewStateListener {
        void sendTextMessage(String message);
    }
}