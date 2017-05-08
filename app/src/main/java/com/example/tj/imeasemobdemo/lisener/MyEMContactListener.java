package com.example.tj.imeasemobdemo.lisener;

import com.example.tj.imeasemobdemo.utils.LogUtils;
import com.hyphenate.EMContactListener;

/**
 * Created by tsj029 on 2017/5/5.
 */

public class MyEMContactListener implements EMContactListener {

    @Override
    public void onContactInvited(String username, String reason) {
        //收到好友邀请
        LogUtils.d("收到好友邀请 "+username);
    }

    @Override
    public void onFriendRequestAccepted(String username) {
        //好友请求被同意
        LogUtils.d("好友请求被同意 "+username);
    }

    @Override
    public void onFriendRequestDeclined(String username) {
        //好友请求被拒绝
        LogUtils.d("好友请求被拒绝 "+username);
    }

    @Override
    public void onContactDeleted(String username) {
        //被删除时回调此方法
        LogUtils.d("被删除时回调此方法 "+username);
    }


    @Override
    public void onContactAdded(String username) {
        //增加了联系人时回调此方法
        LogUtils.d("增加了联系人时回调此方法 "+username);
    }
}
