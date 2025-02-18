package com.example.unitywrapper;
public interface JavaMsgReceiver {
    void javaMsg(String funName, String funArg);

    void myRunOnUI(Runnable run);

    void shuShuInit(String appId, String serverUrl);

    void shuShuLogin(String uin);

    void sdkHasInit();
}
