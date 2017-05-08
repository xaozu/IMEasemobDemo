package com.example.tj.imeasemobdemo;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.tj.imeasemobdemo.utils.SpUtils;

/**
 * Created by tsj029 on 2017/5/4.
 */

public class APP extends MultiDexApplication     {
    private static APP sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        sApp=this;
        SpUtils.init(this);
        IMHelper.getInstance().init(this);
    }

    public static APP getApplication(){
        return sApp;
    }

    public static Context getContext(){
        return sApp.getApplicationContext();
    }


}
