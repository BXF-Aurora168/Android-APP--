package com.example.clockappliction.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class ServiceDate extends Service {
    private final String TAG = "SERVICEDATE";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //Service被创建时调用
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate方法被调用!");
        Toast.makeText(this, "onCreate方法被调用!", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    //Service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand方法被调用!", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    //Service被关闭之前回调
    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
