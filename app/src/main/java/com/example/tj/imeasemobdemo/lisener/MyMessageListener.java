package com.example.tj.imeasemobdemo.lisener;

import android.util.Log;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by tsj029 on 2017/5/4.
 * 消息监听
 */

public class MyMessageListener implements EMMessageListener {
    private String TAG="MyMessageListener";
    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        //收到消息
        Log.d(TAG,"收到消息 "+messages.toString());
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        //收到透传消息
        Log.d(TAG,"收到透传消息 "+messages.toString());
    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {
        //收到已读回执
        Log.d(TAG,"收到已读回执 "+messages.toString());
    }

    @Override
    public void onMessageDelivered(List<EMMessage> message) {
        //收到已送达回执
        Log.d(TAG,"收到已送达回执 "+message.toString());
    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {
        //消息状态变动
        Log.d(TAG,"消息状态变动 "+message.toString());
    }
}
