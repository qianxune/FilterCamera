package com.scorpio.camera.base.app;

import android.util.Log;

import com.scorpio.library.base.app.LibApplication;
public class BaseApplication extends LibApplication {

    /**
     * 程序启动的时候执行
     */
    @Override
    public void onCreate() {
        Log.d("Application", "onCreate");
        super.onCreate();
    }
}


