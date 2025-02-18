package com.example.tanwansdk;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.wanzi.WanziApplication;

public class TWApplication extends WanziApplication {
    private String TAG = "TWApplication";
    @Override
    public void onCreate() {
        Log.d("xxxxonCreate", "onCreate: TWApplication");
        super.onCreate();
    }

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
