package com.example.tj.imeasemobdemo.lisener;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.util.NetUtils;

/**
 * Created by tsj029 on 2017/5/4.
 * 连接状态监听
 */

public class MyConnectionListener implements EMConnectionListener {
    private String TAG="ConnectionListener";
    private Context mContext;

    public MyConnectionListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onConnected() {
        Log.d(TAG,"连接成功");
    }

    @Override
    public void onDisconnected(final int errorCode) {

                if(errorCode == EMError.USER_REMOVED){
                    // 显示帐号已经被移除
                    Log.d(TAG,"显示帐号已经被移除");
                }else if (errorCode == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    // 显示帐号在其他设备登录
                    Log.d(TAG,"显示帐号在其他设备登录");
                } else {
                    if (NetUtils.hasNetwork(mContext)) {
                        //连接不到聊天服务器
                        Log.d(TAG,"连接到聊天服务器");
                    }else {
                        //当前网络不可用，请检查网络设置
                        Log.d(TAG,"当前网络不可用");

                    }
            }
    }
}
