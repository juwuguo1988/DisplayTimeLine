package com.example.administrator.timeline.utils;


import com.example.administrator.timeline.common.XZLApplication;

/**
 * UI工具类，包括dp，px，sp的转换；获取状态栏高度，弹出Toast...
 */
public class UIUtils {

    public static int px2dp(float pxValue) {
        return (int) (pxValue / XZLApplication.screenDensity + 0.5f);
    }

    public static int dp2px(float dpValue) {
        return (int) (dpValue * XZLApplication.screenDensity + 0.5f);
    }

    public static int px2sp(float pxValue) {
        return (int) (pxValue / XZLApplication.scaledDensity);
    }

    public static int sp2px(float spValue) {
        return (int) (spValue * XZLApplication.scaledDensity);
    }

}
