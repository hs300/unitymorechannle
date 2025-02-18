package com.example.sanqi;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;

import com.example.unitywrapper.MainActivity;
import com.example.unitywrapper.SDKProxy;
import com.tanwan.game.sdk.TWCode;
import com.tanwan.game.sdk.TwSDKCallBack;
import com.tanwan.gamesdk.TwAPI;
import com.tanwan.gamesdk.net.model.ExtensionBean;
import com.tanwan.gamesdk.net.model.TwUser;

public class SanQiSDK extends SDKProxy {
    @Override
    public String callFunWithArg(String funName, String arg) {
        return null;
    }

    @Override
    public void onCreate(Activity activity, Bundle saveInstanceState) {
        super.onCreate(activity, saveInstanceState);
        TwAPI.getInstance().setScreenOrientation(TwAPI.SCREEN_ORIENTATION_PORTRAIT);
        TwAPI.getInstance().showAgreement(_mainActivity, () -> initTanWanSDK());
        CountDownTimer countDownTimer = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long l) {

            }
            @Override
            public void onFinish() {
                TwAPI.getInstance().login(_mainActivity);
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onPause() {
        TwAPI.getInstance().onPause();
    }

    @Override
    public void onResume() {
        TwAPI.getInstance().onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        TwAPI.getInstance().onConfigurationChanged(newConfig);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        TwAPI.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        TwAPI.getInstance().onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        TwAPI.getInstance().onDestroy();
    }

    @Override
    public void onStop() {
        TwAPI.getInstance().onStop();
    }

    @Override
    public void onStart() {
        TwAPI.getInstance().onStart();
    }

    @Override
    public void onRestart() {
        TwAPI.getInstance().onRestart();
    }

    @Override
    public void onNewIntent(Intent intent) {
        TwAPI.getInstance().onNewIntent(intent);
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
        TwAPI.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        TwAPI.getInstance().onWindowFocusChanged(hasFocus);
    }

    private void initTanWanSDK(){
        TwAPI.getInstance().initSDK(_mainActivity, _saveInstanceState, new TwSDKCallBack() {
            @Override
            public void onInitResult(int i) {
                _sdkHasInit = true;
                m_SDKDelegate.sdkHasInit();
                m_SDKDelegate.shuShuInit("xxx", "xxx");
            }

            @Override
            public void onLogoutResult(int i) {
                m_SDKDelegate.shuShuLogin("99200599");
            }
            @Override
            public void onLoginResult(TwUser twUser) {

            }

            @Override
            public void onExitResult(boolean b) {

            }



            @Override
            public void onPayResult(int i) {
                switch (i) {
                    case TWCode.CODE_PAY_SUCCESS://支付成功

                        break;
                    case TWCode.CODE_PAY_FAIL://支付失败


                        break;
                    case TWCode.CODE_PAY_CANCEL://支付取消

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onExtension(ExtensionBean extensionBean) {

            }

            @Override
            public void onDeleteAccountResult(boolean b) {

            }
        });
    }
}
