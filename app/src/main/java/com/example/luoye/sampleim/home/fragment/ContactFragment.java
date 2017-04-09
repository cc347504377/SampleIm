package com.example.luoye.sampleim.home.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.luoye.sampleim.R;
import com.example.luoye.sampleim.home.presenter.ContactPresenter;
import com.example.luoye.sampleim.util.FactoryUtils;
import com.example.luoye.sampleim.util.PublicCallback;
import com.example.luoye.sampleim.util.PublicUtils;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.widget.EaseTitleBar;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by luoye on 2017/4/9.
 */

public class ContactFragment extends EaseContactListFragment implements FactoryUtils.DialogClickListner {

    Unbinder unbinder;
    @BindView(R.id.title_bar)
    EaseTitleBar titleBar;
    @BindView(R.id.query)
    EditText query;
    private ContactPresenter presenter;
    private AlertDialog addDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ease_fragment_contact_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        //初始化presenter
        presenter = ContactPresenter.getInstance();
        //初始化数据
        titleBar.setRightImageResource(R.drawable.command_add);
        titleBar.setRightLayoutClickListener(new AddContactListener());
        presenter.initList(new PublicCallback.DataCallback<List<String>>() {
            @Override
            public void onSuccess(List<String> strings) {
                PublicUtils.log(strings.toString());
            }

            @Override
            public void onFailed(String msg) {

            }
        });
        //初始化添加联系人
        addDialog = FactoryUtils.getDialogWithEidt(getContext(), this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.search_clear)
    public void onViewClicked() {

    }

    /**
     *联系人添加对话框监听
     */
    @Override
    public void onClick(String s) {
        presenter.addContact(s, new PublicCallback.Callback() {
            @Override
            public void onSuccess() {
                PublicUtils.toast("添加成功");
            }

            @Override
            public void onFailed(String msg) {
                PublicUtils.toast("添加失败");
            }
        });
    }

    /**
     *弹出添加对话框监听
     */
    private class AddContactListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            addDialog.show();
        }
    }
}
