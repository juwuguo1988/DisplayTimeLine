package com.example.administrator.timeline.common;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

public class XZLApplication extends Application {

    public static int verticalScreenWidth;      //竖屏时屏幕的宽度
    public static int verticalScreenHeight;     //竖屏时屏幕的高度
    public static int horizontalScreenWidth;    //横屏时屏幕的宽度
    public static int horizontalScreenHeight;   //横屏时屏幕的高度
    public static float screenDensity;          //密度
    public static float scaledDensity;

    private static Context mContext;
    private boolean isLogin = false;
    private static int APP_ACTIVITY_COUNT = 0;
    private static final String BUGLY_APP_ID = "41ebbbf4cf";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化上下文
        mContext = getApplicationContext();

        getScreenSize();

    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取屏幕分辨率方法
     */
    private void getScreenSize() {
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        screenDensity = dm.density;
        scaledDensity = dm.scaledDensity;
        verticalScreenWidth = dm.widthPixels;
        verticalScreenHeight = dm.heightPixels;
        horizontalScreenWidth = verticalScreenHeight;
        horizontalScreenHeight = verticalScreenWidth;
    }
}
