package com.example.luoye.sampleim.Login.presenter;

import android.os.Handler;

import com.example.luoye.sampleim.Login.model.LoginModel;
import com.example.luoye.sampleim.util.PublicCallback;
import com.example.luoye.sampleim.util.PublicUtils;
import com.hyphenate.EMCallBack;

/**
 * Created by luoye on 2017/4/9.
 */

public class LoginPresenter {
    private static final LoginPresenter ourInstance = new LoginPresenter();
    private final LoginModel model;

    public static LoginPresenter getInstance() {
        return ourInstance;
    }

    private LoginPresenter() {
        model = LoginModel.getInstance();
    }

    /**
     * 登录
     */

    public interface LoginCallback {
        void onStart();

        void onSuccess();

        void onFailed(String msg);
    }

    public void login(String userName, String passWord, final Handler handler, final LoginCallback callback) {

        if (userName.trim().equals("")) {
            PublicUtils.toast("用户名不能为空");
            return;
        }
        if (passWord.trim().equals("")) {
            PublicUtils.toast("密码不能为空");
            return;
        }

        callback.onStart();
        model.login(userName, passWord, new EMCallBack() {
            @Override
            public void onSuccess() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess();
                    }
                });
            }

            @Override
            public void onError(int i, final String s) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        PublicUtils.log(s);
                        callback.onFailed(s);
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    /**
     * 注册
     */
    public void signUp(String userName, String passWord, final LoginCallback callback) {

        if (userName.trim().equals("")) {
            PublicUtils.toast("用户名不能为空");
            return;
        }
        if (passWord.trim().equals("")) {
            PublicUtils.toast("密码不能为空");
            return;
        }

        callback.onStart();

        model.signUp(userName, passWord, new PublicCallback.Callback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onFailed(String msg) {
                callback.onFailed(msg);
            }
        });
    }
}
