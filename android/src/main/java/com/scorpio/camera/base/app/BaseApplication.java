package com.scorpio.camera.base.app;

import android.os.AsyncTask;
import android.util.Log;

import com.scorpio.library.base.app.LibApplication;
import com.tzutalin.dlib.FaceDet;

public class BaseApplication extends LibApplication {

    /**
     * 程序启动的时候执行
     */
    @Override
    public void onCreate() {
        Log.d("Application", "onCreate");
        super.onCreate();
        FaceDet.init();
    }
}


