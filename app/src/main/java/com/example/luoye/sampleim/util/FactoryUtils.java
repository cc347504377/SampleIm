package com.example.luoye.sampleim.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luoye.sampleim.R;

/**
 * Created by luoye on 2017/4/8.
 */

public class FactoryUtils {

    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("加载中");
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static ProgressDialog getHorizontalProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return progressDialog;
    }

    public interface DialogClickListner {
        void onClick(String s);
    }

    public static AlertDialog getDialogWithEidt(Context context, final DialogClickListner listner) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_add_contact, null);
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(true)
                .create();
        final EditText edit = (EditText) view.findViewById(R.id.addContact_edit);
        Button button = (Button) view.findViewById(R.id.addContact_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listner.onClick(edit.getText().toString());
            }
        });
        return dialog;
    }
}
