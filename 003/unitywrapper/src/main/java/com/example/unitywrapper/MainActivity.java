package com.example.unitywrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.unity3d.player.UnityPlayerActivity;
import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import cn.thinkingdata.analytics.TDConfig;
import cn.thinkingdata.analytics.ThinkingAnalyticsSDK;

public class MainActivity extends UnityPlayerActivity implements JavaMsgReceiver {
    private SDKProxy m_SDKProxy;

    private ThinkingAnalyticsSDK tDataInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String sdkClassName = applicationInfo.metaData.getString("ProxyName");
            Log.i("MainActivity", sdkClassName);
            Class<?> clazz = Class.forName(sdkClassName);
            m_SDKProxy = (SDKProxy)clazz.newInstance();
            m_SDKProxy.setDelegate(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m_SDKProxy.onCreate(this,savedInstanceState);
        String packageName = this.getPackageName();
        Log.i("appid", packageName);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void javaMsg(String funName, String funArg){
        try {
            Class<?> myClass = this.getClass();
            Method method = myClass.getDeclaredMethod(funName, String.class);
            if(method != null) {
                method.setAccessible(true);
                method.invoke(this,funArg);
            }
            else {
                Log.i("no handler funName ==  ", funName);
            }
        } catch (Exception e) {
            System.out.println("Exceptionxxxx");
        }
    }


    public void shuShuInit(String appId, String serverUrl){
        String shuShuId = getString(R.string.shushu_id);
        String shuShuServerUrl = getString(R.string.shushu_server_url);

        Utils.log(shuShuId);
        Utils.log(shuShuServerUrl);
//        TDConfig config = TDConfig.getInstance(this, shuShuId, shuShuServerUrl);
//        config.setMode(TDConfig.ModeEnum.DEBUG);
//        tDataInstance = ThinkingAnalyticsSDK.sharedInstance(config);
//        tDataInstance.identify(tDataInstance.getDeviceId());
//        //设置完访客ID与公共属性后，再开启自动采集
//        List<ThinkingAnalyticsSDK.AutoTrackEventType> eventTypeList = new ArrayList<>();
//        eventTypeList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_INSTALL);
//        eventTypeList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_START);
//        eventTypeList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_END);
//        eventTypeList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_VIEW_SCREEN);
//        eventTypeList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CLICK);
//        eventTypeList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CRASH);
//        ThinkingAnalyticsSDK.sharedInstance(this, CommonDefine.TA_APP_ID).enableAutoTrack(eventTypeList);
        // 初始化SDK
        try{
            JSONObject userData = new JSONObject();
            userData.put("game_bundle_id",getPackageName());
            tDataInstance.user_setOnce(userData);
        }
        catch (Exception e){
        }
    }

    @Override
    public void shuShuLogin(String uin) {
        Utils.log(uin);
    }

    @Override
    public void sdkHasInit() {

    }

    private void receiverSDKInit(String arg){
        Log.i("ReceiverSDKInit", arg);
    }


    public void myRunOnUI(Runnable run){
        runOnUiThread(run);
    }

}