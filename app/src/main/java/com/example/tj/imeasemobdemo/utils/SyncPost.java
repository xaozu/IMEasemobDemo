package com.example.tj.imeasemobdemo.utils;

/**
 * Created by tsj029 on 2017/5/8.
 */

public class SyncPost  {

    boolean end = false;
    Runnable runnable;

    SyncPost(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        synchronized (this) {
            runnable.run();
            end = true;
            try {
                this.notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void waitRun() {
        if (!end) {
            synchronized (this) {
                if (!end) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
