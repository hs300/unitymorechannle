package com.example.tanwansdk;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;

import com.example.unitywrapper.MainActivity;
import com.example.unitywrapper.SDKProxy;
import com.wanzi.SDKCallBack;
import com.wanzi.sdk.API;
import com.wanzi.sdk.model.ExtensionBean;
import com.wanzi.sdk.model.SDKUser;

public class TanWanSDK extends SDKProxy {
    @Override
    public String callFunWithArg(String funName, String arg) {
        return null;
    }

    @Override
    public void onCreate(Activity activity, Bundle saveInstanceState) {
        super.onCreate(activity, saveInstanceState);
        API.getInstance().onCreate(activity, _saveInstanceState);

        API.getInstance().initSDK(activity, _saveInstanceState, new SDKCallBack() {
            @Override
            public void onInitResult(int i) {
                _sdkHasInit = true;
                m_SDKDelegate.shuShuInit("xxx", "xxx");
            }

            @Override
            public void onLogoutResult(int i) {
                m_SDKDelegate.shuShuLogin("99200599");
            }

            @Override
            public void onExitResult(boolean b) {

            }

            @Override
            public void onLoginResult(SDKUser sdkUser) {

            }

            @Override
            public void onPayResult(int i) {

            }

            @Override
            public void onExtension(ExtensionBean extensionBean) {

            }

            @Override
            public void onAuthentication(SDKUser sdkUser) {

            }
        });

        m_SDKDelegate.myRunOnUI(new Runnable() {
            @Override
            public void run() {

            }
        });

        CountDownTimer countDownTimer = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                m_SDKDelegate.myRunOnUI(new Runnable() {
                    @Override
                    public void run() {
                        API.getInstance().login(_mainActivity);
                    }
                });
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onPause() {
        API.getInstance().onPause(_mainActivity);
    }

    @Override
    public void onResume() {
        API.getInstance().onResume(_mainActivity);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        API.getInstance().onConfigurationChanged(newConfig);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        API.getInstance().onActivityResult(requestCode, resultCode,
                data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        API.getInstance().onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        API.getInstance().onDestroy(_mainActivity);
    }

    @Override
    public void onStop() {
        API.getInstance().onStop(_mainActivity);
    }

    @Override
    public void onStart() {
        API.getInstance().onStart(_mainActivity);
    }

    @Override
    public void onRestart() {
        API.getInstance().onRestart(_mainActivity);
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
        API.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        API.getInstance().onWindowFocusChanged(hasFocus);
    }
}
