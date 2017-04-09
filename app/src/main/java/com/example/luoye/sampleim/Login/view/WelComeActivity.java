package com.example.luoye.sampleim.Login.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luoye.sampleim.R;
import com.example.luoye.sampleim.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelComeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(WelComeActivity.this, LoginActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    protected void initView() {

    }
}
