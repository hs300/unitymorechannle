package com.example.unitywrapper;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;

// extends activity is not good
public abstract class SDKProxy   {
    protected JavaMsgReceiver m_SDKDelegate;
    protected Activity _mainActivity;
    protected Bundle _saveInstanceState;
    protected boolean _sdkHasInit = false;
    public void setDelegate(JavaMsgReceiver delegate){
        m_SDKDelegate = delegate;
    }
    public abstract String callFunWithArg(String funName, String arg);

    public void onCreate(Activity activity, Bundle saveInstanceState){
        _mainActivity = activity;
        _saveInstanceState = saveInstanceState;
    }
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
