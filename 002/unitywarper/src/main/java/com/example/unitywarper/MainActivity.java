package com.example.unitywarper;


import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity111","MainActivity");
    }
}