package com.example.tj.imeasemobdemo.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tsj029 on 2017/5/8.
 */

public class HandlerPoster extends Handler {
    private final int ASYNC = 0x1;
    private final int SYNC = 0x2;
    private final Queue<Runnable> asyncPool;
    private final Queue<SyncPost> syncPool;
    private final int maxMillisInsideHandleMessage;
    private boolean asyncActive;
    private boolean syncActive;

    HandlerPoster(Looper looper, int maxMillisInsideHandleMessage) {
        super(looper);
        this.maxMillisInsideHandleMessage = maxMillisInsideHandleMessage;
        asyncPool = new LinkedList<>();
        syncPool = new LinkedList<>();
    }

    void dispose() {
        this.removeCallbacksAndMessages(null);
        this.asyncPool.clear();
        this.syncPool.clear();
    }

    void async(Runnable runnable) {
        synchronized (asyncPool) {
            asyncPool.offer(runnable);
            if (!asyncActive) {
                asyncActive = true;
                if (!sendMessage(obtainMessage(ASYNC))) {
                }
            }
        }
    }
}
