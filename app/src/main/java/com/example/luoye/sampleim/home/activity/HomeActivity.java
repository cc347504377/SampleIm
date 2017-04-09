package com.example.luoye.sampleim.home.activity;

;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.example.luoye.sampleim.R;
import com.example.luoye.sampleim.base.BaseActivity;
import com.example.luoye.sampleim.home.fragment.ContactFragment;
import com.example.luoye.sampleim.home.fragment.ConversationFragment;
import com.example.luoye.sampleim.home.fragment.SetFragment;
import com.example.luoye.sampleim.util.PublicUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_frame)
    FrameLayout homeFrame;
    private List<Fragment> fragmentList;
    private Fragment conversationFragment;
    private Fragment contactFragment;
    private Fragment setFragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        initFragment();
    }

    private void initFragment() {
        //初始化list
        fragmentList = new ArrayList<>();
        conversationFragment = new ConversationFragment();
        fragmentList.add(conversationFragment);
        //初始化fragment
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.home_frame, conversationFragment);
        transaction.commit();
    }

    @OnClick({R.id.home_tab_conversation, R.id.home_tab_contact, R.id.home_tab_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_tab_conversation:
                selectTab(conversationFragment);
                break;
            case R.id.home_tab_contact:
                if (contactFragment == null) {
                    contactFragment = new ContactFragment();
                    fragmentList.add(contactFragment);
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.home_frame, contactFragment);
                    transaction.commit();
                }
                selectTab(contactFragment);
                break;
            case R.id.home_tab_set:
                if (setFragment == null) {
                    setFragment = new SetFragment();
                    fragmentList.add(setFragment);
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.home_frame, setFragment);
                    transaction.commit();
                }
                selectTab(setFragment);
                break;
        }
    }

    private void selectTab(Fragment fragment) {
        FragmentTransaction beginTransaction = manager.beginTransaction();
        for (Fragment tab : fragmentList) {
            beginTransaction.hide(tab);
        }
        beginTransaction.show(fragment);
        beginTransaction.commit();
    }
}
