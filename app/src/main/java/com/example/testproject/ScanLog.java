package com.example.testproject;

import android.util.Log;

/**
 * author : 唐鹏聪
 * date : 2022/6/16 12:39
 * description :
 */
public class ScanLog {

    private static StringBuilder getBase() {
        StringBuilder stringBuilder = new StringBuilder();
        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
        String activityName = Thread.currentThread().getStackTrace()[4].getFileName();
        return stringBuilder.append(activityName).append("==")
                .append(methodName).append("==");
    }

    public static void log(String msg) {
        Log.e("TAG", getBase().append(msg).toString());
    }

}
