package com.example.tj.imeasemobdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tj.imeasemobdemo.R;
import com.example.tj.imeasemobdemo.activity.ChatActivity;
import com.hyphenate.easeui.EaseConstant;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by tsj029 on 2017/5/4.
 */

public class FriendAdapter extends ItemViewProvider<String,FriendAdapter.FriendViewHolder>{

    private Context mContext;

    public FriendAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected FriendViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new FriendViewHolder(inflater.inflate(R.layout.item_consversation,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull FriendViewHolder holder, @NonNull final String s) {
        holder.tvConversation.setText(s);
        holder.linConversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mContext.startActivity(new Intent(mContext, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID,s));
            }
        });
    }


    public class FriendViewHolder extends RecyclerView.ViewHolder{
        TextView tvConversation;
        LinearLayout linConversation;
        public FriendViewHolder(View itemView) {
            super(itemView);
            tvConversation= (TextView) itemView.findViewById(R.id.tv_conversation);
            linConversation= (LinearLayout) itemView.findViewById(R.id.lin_conversation);
        }
    }
}
