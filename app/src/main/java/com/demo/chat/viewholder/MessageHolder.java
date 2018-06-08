package com.demo.chat.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.androidbootstrap.R;
import com.demo.domain.chat.models.Message;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.message_layout)
    RelativeLayout messageLayout;

    @BindView(R.id.message_text)
    TextView messageText;

    private Context mContext;
    private boolean isSameSender;
    private boolean isAddressedToMe;

    public MessageHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    public void setMessage(Message message) {
        setBody(message);
        setMessageBackgroundLayoutParams(message);
    }

    private void setBody(Message message) {
        messageText.setText(message.getMessage());
    }

    private void setMessageBackgroundLayoutParams(Message message) {
        RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) messageLayout.getLayoutParams();
        int topMargin = mContext.getResources().getDimensionPixelSize(R.dimen.module_3);
        int bottomMargin = topMargin;//same margin from top and boottom

        if (isAddressedToMe) {
            int leftMargin = 0;
            leftMargin = mContext.getResources().getDimensionPixelSize(R.dimen.module_zero_dp);

            messageLayout.setBackgroundResource(!isSameSender ? R.mipmap.bg_message : R.mipmap.bubble_final_other_user);

            relativeParams.setMargins(leftMargin, topMargin, 0, bottomMargin);
            relativeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else {
            int rightMargin = mContext.getResources().getDimensionPixelSize(R.dimen.module_zero_dp);

            messageLayout.setBackgroundResource(!isSameSender ? R.mipmap.white_message_box : R.mipmap.bubble_message_white);

            relativeParams.setMargins(0, topMargin, rightMargin, bottomMargin);
            relativeParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        messageLayout.setLayoutParams(relativeParams);
    }

    public void setSameSender(boolean sameSender) {
        isSameSender = sameSender;
    }

    public void setAddressedToMe(boolean addressedToMe) {
        isAddressedToMe = addressedToMe;
    }
}
