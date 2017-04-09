package com.example.luoye.sampleim.Login.model;

import com.example.luoye.sampleim.util.PublicCallback;
import com.example.luoye.sampleim.util.PublicUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luoye on 2017/4/9.
 */

public class LoginModel {
    private static final LoginModel ourInstance = new LoginModel();

    public static LoginModel getInstance() {
        return ourInstance;
    }

    private LoginModel() {
    }

    public void login(String userName, String passWord, EMCallBack callBack) {
        EMClient.getInstance().login(userName, passWord, callBack);
    }

    public void signUp(final String userName, final String passWord, final PublicCallback.Callback callback) {
        Observable
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        e.onNext(1);
                        e.onComplete();
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        EMClient.getInstance().createAccount(userName, passWord);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        PublicUtils.log(e.toString());
                        callback.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onSuccess();
                    }
                });

    }
}
