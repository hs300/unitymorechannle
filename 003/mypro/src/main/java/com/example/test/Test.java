package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.unitywrapper.MainActivity;
import com.example.unitywrapper.SDKProxy;
import com.example.unitywrapper.Utils;

public class Test extends SDKProxy {
    @Override
    public void onCreate(Activity activity, Bundle saveInstanceState) {
        super.onCreate(activity, saveInstanceState);

        Utils.log("Test init");
    }

    @Override
    public String callFunWithArg(String funName, String arg) {
        return null;
    }


    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onBackClick() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }
}
