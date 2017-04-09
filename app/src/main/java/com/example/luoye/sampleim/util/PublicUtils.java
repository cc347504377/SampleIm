package com.example.luoye.sampleim.util;

import android.util.Log;
import android.widget.Toast;

import com.example.luoye.sampleim.MyApplication;

/**
 * Created by luoye on 2017/4/8.
 */

public class PublicUtils {

    public static void toast(String msg) {
        Toast.makeText(MyApplication.context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void log(String msg) {
        Log.i("TAG", msg);
    }
}
