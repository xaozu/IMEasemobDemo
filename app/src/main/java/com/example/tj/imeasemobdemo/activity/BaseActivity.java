package com.example.tj.imeasemobdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：zhulunjun
 * Time：16/7/28
 * description：activity 的基类
 */
public abstract class BaseActivity extends AppCompatActivity{
    private Unbinder unbind;
    //得到页面布局
    protected abstract int getLayoutView();
    //得到页面
    protected abstract BaseActivity getActivity();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        unbind= ButterKnife.bind(getActivity());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }
}
