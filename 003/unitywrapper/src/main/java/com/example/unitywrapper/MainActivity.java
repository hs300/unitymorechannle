package com.example.unitywrapper;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity implements SDKDelegate {
    private SDKProxy m_SDKProxy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String sdkClassName = applicationInfo.metaData.getString("ProxyName");
            Log.i("MainActivity", sdkClassName);
            // 获取Class对象，参数是类的完全限定名
            Class<?> clazz = Class.forName(sdkClassName);
            // 使用Class对象创建实例
            m_SDKProxy = (SDKProxy)clazz.newInstance();
            m_SDKProxy.setDelegate(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m_SDKProxy.onCreate(this,savedInstanceState);


        String packageName = this.getPackageName();

        Log.i("appid", packageName);
    }



    @Override
    public void initSuccess() {

    }

    @Override
    public void initFailed() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void logoutSuccess() {

    }

    @Override
    public void paySuccess() {

    }

    @Override
    public void payFailed() {

    }
}