package com.example.luoye.sampleim.Login.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.luoye.sampleim.Login.presenter.LoginPresenter;
import com.example.luoye.sampleim.R;
import com.example.luoye.sampleim.base.BaseActivity;
import com.example.luoye.sampleim.util.FactoryUtils;
import com.example.luoye.sampleim.util.PublicUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_name_edit)
    TextInputEditText loginNameEdit;
    @BindView(R.id.login_ps_edit)
    TextInputEditText loginPsEdit;
    @BindView(R.id.login_r_ps_edit)
    TextInputEditText loginRPsEdit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        initToolbar(toolbar);
        progressDialog = FactoryUtils.getProgressDialog(this);
    }

    @OnClick({R.id.login_register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_register_btn:
                signUp();
                break;
        }
    }

    private void signUp() {
        String userName = loginNameEdit.getText().toString();
        String passWord = loginPsEdit.getText().toString();
        String passWordRe = loginRPsEdit.getText().toString();
        if (!passWord.equals(passWordRe)) {
            PublicUtils.toast("两次输入密码不相同，请重新输入");
            return;
        }
        LoginPresenter.getInstance().signUp(userName, passWord, new LoginPresenter.LoginCallback() {
            @Override
            public void onStart() {
                progressDialog.show();
            }

            @Override
            public void onSuccess() {
                progressDialog.dismiss();
                PublicUtils.toast("注册成功");
                finish();
            }

            @Override
            public void onFailed(String msg) {
                progressDialog.dismiss();
                PublicUtils.toast("注册失败：" + msg);
            }
        });
    }
}
