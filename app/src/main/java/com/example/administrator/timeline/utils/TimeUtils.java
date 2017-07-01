package com.example.administrator.timeline.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtils {

    private static final int INTERVAL = 1000 * 60 * 60 * 24;// 24h
    public static final SimpleDateFormat DATE_SDF_YMD = new SimpleDateFormat("yyyy-MM-dd");

    public static String isWeekStartDay() {
        Date dBefore;
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -15);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        return defaultStartDate;
    }

    public static String isWeekStartYYMMDDDay() {
        Date dBefore;
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -15);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        return defaultStartDate;
    }

    public static String isWeekMiddleDay() {
        Date dBefore;
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -9);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        return defaultStartDate;
    }

    public static String isWeekAndMonthEndDay() {
        Date dBefore;
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -2);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        return defaultStartDate;
    }

    public static String isMonthStartYYMMDDDay() {
        Date dBefore;
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -31);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        return defaultStartDate;
    }

    public static String isMonthStartDay() {
        Date dBefore;
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -31);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        return defaultStartDate;
    }

    public static String isMonthMiddleDay() {
        Date dBefore;
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -16);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        return defaultStartDate;
    }

    public static long getDiffFromDay(String endDay, String startDay) {
        long result = 0;
        try {
            result = (DATE_SDF_YMD.parse(startDay).getTime() - DATE_SDF_YMD.parse(endDay).getTime()) / INTERVAL;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
