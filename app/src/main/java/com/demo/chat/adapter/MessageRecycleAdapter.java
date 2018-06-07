package com.demo.chat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.androidbootstrap.R;
import com.demo.chat.viewholder.MessageHolder;
import com.demo.domain.chat.models.Message;

import java.util.List;

import javax.inject.Inject;

public class MessageRecycleAdapter extends RecyclerView.Adapter<MessageHolder>{

    private List<Message> mMessages;

    @Inject
    public MessageRecycleAdapter(){
    }

    @Override
    public int getItemCount() {
        return mMessages == null ? 0 : mMessages.size();
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_text_message, parent, false);
        MessageHolder holder = new MessageHolder(parent.getContext(), view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Message message = mMessages.get(position);
        Message previousMessage = position == 0 ? null : mMessages.get(position - 1);
        holder.setSameSender(isSameSender(message, previousMessage));
        holder.setAddressedToMe(isAddressedToMe(message));
        holder.setMessage(message);
    }

    public void setMessages(List<Message> messages) {
        this.mMessages = messages;
        notifyDataSetChanged();
    }

    private boolean isSameSender(final Message message, final Message prevMessage) {
        if (message == null || prevMessage == null) return false;
        boolean m1AddressedToMe = isAddressedToMe(message);
        boolean m2AddressedToMe = isAddressedToMe(prevMessage);
        return (m1AddressedToMe && m2AddressedToMe) || (!m1AddressedToMe && !m2AddressedToMe);
    }

    private boolean isAddressedToMe(Message message) {
        return message.getDirection().equalsIgnoreCase("INCOMING");
    }
}
