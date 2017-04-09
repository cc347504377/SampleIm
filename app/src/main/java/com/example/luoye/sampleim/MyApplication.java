package com.example.luoye.sampleim;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by luoye on 2017/4/8.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局Context
        context = this;
        //初始化环信
        EMOptions emOptions = new EMOptions();
        emOptions.setAcceptInvitationAlways(true);
        EaseUI.getInstance().init(this, emOptions);
    }
}
