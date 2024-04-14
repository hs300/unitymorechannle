package com.example.unitywrapper;
public interface SDKDelegate{
    void initSuccess();
    void initFailed();
    void loginSuccess();
    void loginFailed();
    void logoutSuccess();
    void paySuccess();
    void payFailed();

}
