package com.android.volleyclient;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhaoruolei1992 on 2016/5/1.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
