package com.example.unitywrapper;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public abstract class SDKProxy {
    protected SDKDelegate m_SDKDelegate;
    public void setDelegate(SDKDelegate delegate){
        m_SDKDelegate = delegate;
    }
    public abstract void sdkInit();
    public abstract void sdkLogin();
    public abstract String callFunWithArg(String funName, String arg);


    public abstract void onCreate(Activity activity, Bundle saveInstanceState);
    public abstract void onPause();
    public abstract void onResume();
    public abstract void onConfigurationChanged(Configuration newConfig);
    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);
    public abstract void onSaveInstanceState(Bundle outState);
    public abstract void onDestroy();
    public abstract void onStop();
    public abstract void onStart();
    public abstract void onRestart();
    public abstract void onNewIntent(Intent intent);
    public abstract void onBackClick();
    public abstract boolean onKeyDown(int keyCode, KeyEvent event);
    public abstract void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    public abstract void onWindowFocusChanged(boolean hasFocus);
}
