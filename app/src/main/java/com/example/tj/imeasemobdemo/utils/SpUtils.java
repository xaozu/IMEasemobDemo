/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tj.imeasemobdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;


public class SpUtils {
    /**
     * 保存Preference的name
     */
    public static final String PREFERENCE_NAME = "sp_im";
    private static SharedPreferences mSharedPreferences;
    private static SpUtils mPreferencemManager;
    private static SharedPreferences.Editor editor;
    private static final String USERNAME="username";

    private SpUtils(Context cxt) {
        mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public static synchronized void init(Context cxt) {
        if (mPreferencemManager == null) {
            mPreferencemManager = new SpUtils(cxt);
        }
    }


    /**
     * 单例模式，获取instance实例
     *
     * @param
     * @return
     */
    public synchronized static SpUtils getInstance() {
        if (mPreferencemManager == null) {
            throw new RuntimeException("please init first!");
        }

        return mPreferencemManager;
    }


    public void saveUserName(String username){
        editor.putString(USERNAME,username);
        editor.commit();
    }
    public String getUserName(){
        return mSharedPreferences.getString(USERNAME,null);
    }
    /**
     * SP中写入String类型value
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, @Nullable String value) {
        editor.putString(key, value).apply();
        editor.commit();
    }

    /**
     * SP中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code null}
     */
    public String getString(String key) {
        return mPreferencemManager.getString(key);
    }

    /**
     * SP中写入int类型value
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, int value) {
        editor.putInt(key, value).apply();
        editor.commit();
    }

    /**
     * SP中读取int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * SP中读取int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public int getInt(String key, int defaultValue) {
        return mPreferencemManager.getInt(key, defaultValue);
    }
}
