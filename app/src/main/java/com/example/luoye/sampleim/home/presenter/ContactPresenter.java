package com.example.luoye.sampleim.home.presenter;

import com.example.luoye.sampleim.home.model.ContactModel;
import com.example.luoye.sampleim.util.PublicCallback;

import java.util.List;

/**
 * Created by luoye on 2017/4/9.
 */

public class ContactPresenter {
    private static final ContactPresenter ourInstance = new ContactPresenter();
    private final ContactModel model;

    public static ContactPresenter getInstance() {
        return ourInstance;
    }

    private ContactPresenter() {
        model = ContactModel.getInstance();
    }

    public void initList(PublicCallback.DataCallback<List<String>> callback) {
        model.initList(callback);
    }

    public void addContact(String userName, PublicCallback.Callback callback) {
        model.addContact(userName, callback);
    }
}
