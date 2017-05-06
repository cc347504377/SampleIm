package com.example.luoye.sampleim.chat;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luoye.sampleim.R;
import com.example.luoye.sampleim.home.fragment.ChatFragment;
import com.example.luoye.sampleim.util.PublicUtils;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initFragment();
    }

    private void initFragment() {
        String extra = getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
        PublicUtils.log("extra: " + extra);
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, extra);
        EaseChatFragment chatFragment = new EaseChatFragment();
        chatFragment.setArguments(args);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_main, chatFragment);
        transaction.commit();
    }
}
