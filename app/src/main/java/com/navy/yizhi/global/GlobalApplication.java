package com.navy.yizhi.global;

import android.content.Context;
import android.os.Handler;

import com.mob.MobApplication;


/**
 * Created by Administrator on 2017/12/25.
 */

public class GlobalApplication extends MobApplication {
    private static final String LOG_TAG = "YZ_LOGGER";
    protected static Context context;
    protected static GlobalApplication mApp;
    protected static Handler handler;
    protected static int mainThreadId;

    public static synchronized GlobalApplication getInstance() {
        return mApp;


    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();

    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
}