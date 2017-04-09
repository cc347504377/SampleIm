package com.example.luoye.sampleim.util;

/**
 * Created by luoye on 2017/4/9.
 */

public class PublicCallback {

    public interface Callback{
        void onSuccess();

        void onFailed(String msg);
    }

    public interface DataCallback<T>{
        void onSuccess(T t);

        void onFailed(String msg);
    }

}
