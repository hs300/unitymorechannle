package com.example.unitywrapper;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;

public interface ChannelProxy {
    void sdkInit();
    void sdkLogin();
    String callFunWithArg(String funName, String arg);


    void onCreate(Activity activity, Bundle saveInstanceState);
    void onPause();
    void onResume();
    void onConfigurationChanged(Configuration newConfig);
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onSaveInstanceState(Bundle outState);
    void onDestroy();
    void onStop();
    void onStart();
    void onRestart();
    void onNewIntent(Intent intent);
    void onBackClick();
    boolean onKeyDown(int keyCode, KeyEvent event);
    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    void onWindowFocusChanged(boolean hasFocus);
}
