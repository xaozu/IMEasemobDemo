package com.example.tj.imeasemobdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tj.imeasemobdemo.R;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by tsj029 on 2017/5/4.
 */

public class MessageProvider extends ItemViewProvider<EMMessage,MessageProvider.MessageViewHolder> {

    @NonNull
    @Override
    protected MessageViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MessageViewHolder(inflater.inflate(R.layout.item_consversation,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageViewHolder holder, @NonNull EMMessage message) {
        holder.tvConversation.setText(message.getUserName()+": "+message.getBody());
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView tvConversation;
        public MessageViewHolder(View itemView) {
            super(itemView);
            tvConversation= (TextView) itemView.findViewById(R.id.tv_conversation);
        }
    }
}
