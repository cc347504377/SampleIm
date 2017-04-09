package com.example.luoye.sampleim.Login.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.luoye.sampleim.Login.presenter.LoginPresenter;
import com.example.luoye.sampleim.R;
import com.example.luoye.sampleim.base.BaseActivity;
import com.example.luoye.sampleim.home.activity.HomeActivity;
import com.example.luoye.sampleim.util.FactoryUtils;
import com.example.luoye.sampleim.util.PublicUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_name_edit)
    TextInputEditText loginNameEdit;
    @BindView(R.id.login_ps_edit)
    TextInputEditText loginPsEdit;
    private ProgressDialog progressDialog;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        initToolbar(toolbar);
        progressDialog = FactoryUtils.getProgressDialog(this);
    }



    @OnClick({R.id.login_in_btn, R.id.login_register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_in_btn:
                login();
                break;
            case R.id.login_register_btn:
                startActivity(SignUpActivity.class);
                break;
        }
    }

    private void login() {
        String userName = loginNameEdit.getText().toString();
        String passWord = loginPsEdit.getText().toString();
        LoginPresenter.getInstance().login(userName, passWord,handler, new LoginPresenter.LoginCallback() {
            @Override
            public void onStart() {
                progressDialog.show();
            }

            @Override
            public void onSuccess() {
                progressDialog.dismiss();
                PublicUtils.toast("登录成功");
                startActivity(HomeActivity.class);
                finish();
            }

            @Override
            public void onFailed(String msg) {
                progressDialog.dismiss();
                PublicUtils.toast("登录失败：" + msg);
            }
        });
    }

}
