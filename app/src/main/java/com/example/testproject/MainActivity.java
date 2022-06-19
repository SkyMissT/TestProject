package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        ScanLog.log("aaaa");
    }

    private void test(String aa) {
        Log.e(TAG, "test: " + getBase().append(aa));
    }

    private StringBuilder getBase() {

//        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//        System.out.println("The stackTraceElements length:"+stackTraceElements.length);
//
//        for(int i=0;i<stackTraceElements.length;i++){
//
//            System.out.println("\n---the  "+i+"  element"+"---");
//
//            System.out.println("toString:"+stackTraceElements[i].toString());
//
//            System.out.println("ClassName:"+stackTraceElements[i].getClassName());
//
//            System.out.println("FileName:"+stackTraceElements[i].getFileName());
//
//            System.out.println("LineNumber:"+stackTraceElements[i].getLineNumber());
//
//            System.out.println("MethodName:"+stackTraceElements[i].getMethodName());
//
//        }

        StringBuilder stringBuilder = new StringBuilder();
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        return stringBuilder
                .append(TAG).append("--")
                .append(method).append("--");

    }

}