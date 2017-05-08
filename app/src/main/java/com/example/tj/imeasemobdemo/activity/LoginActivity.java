package com.example.tj.imeasemobdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tj.imeasemobdemo.R;
import com.example.tj.imeasemobdemo.utils.SpUtils;
import com.example.tj.imeasemobdemo.utils.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by tsj029 on 2017/5/4.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;


    @Override
    protected int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected BaseActivity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @OnClick({R.id.btn_login})
    public void myClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login(){
        String username=etUsername.getText().toString();
        String password=etPassword.getText().toString();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            ToastUtils.showDefault("请输入用户名和密码！");
            return;
        }

        //登陆
        EMClient.getInstance().login(username, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
                ToastUtils.showDefault("成功登录 "+EMClient.getInstance().getCurrentUser());
                SpUtils.getInstance().saveUserName(EMClient.getInstance().getCurrentUser());
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！"+message);
                ToastUtils.showDefault("登录失败");
            }
        });
    }

    //保存用户信息
    private void saveUser(){
//        IMHelper.getInstance().
    }

}
