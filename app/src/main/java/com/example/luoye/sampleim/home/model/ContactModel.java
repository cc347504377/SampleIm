package com.example.luoye.sampleim.home.model;

import com.example.luoye.sampleim.util.PublicCallback;
import com.example.luoye.sampleim.util.PublicUtils;
import com.hyphenate.chat.EMClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luoye on 2017/4/9.
 */

public class ContactModel {
    private static final ContactModel ourInstance = new ContactModel();

    public static ContactModel getInstance() {
        return ourInstance;
    }

    private ContactModel() {
    }

    /**
     * 初始化联系人列表
     */
    public void initList(final PublicCallback.DataCallback<List<String>> callback) {
        Observable
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        e.onNext(1);
                        e.onComplete();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<Object, List<String>>() {
                    @Override
                    public List<String> apply(Object o) throws Exception {
                        return EMClient.getInstance().contactManager().getAllContactsFromServer();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> value) {
                        callback.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        PublicUtils.log("加载好友列表失败：\n" + e.toString());
                        callback.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 联系人添加
     */
    public void addContact(final String userName, final PublicCallback.Callback callback) {
        Observable
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        e.onNext(1);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        EMClient.getInstance().contactManager().addContact(userName, "");
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        callback.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        PublicUtils.log("添加联系人失败：\n" + e.toString());
                        callback.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
