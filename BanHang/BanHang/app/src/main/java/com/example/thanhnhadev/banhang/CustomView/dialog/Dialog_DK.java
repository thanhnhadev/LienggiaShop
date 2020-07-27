package com.example.thanhnhadev.banhang.CustomView.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.thanhnhadev.banhang.R;
import com.example.thanhnhadev.banhang.interfaceView.ViewLogin;

public class Dialog_DK extends Dialog {
    private Context mContext;
    private TextView tv_title;
    private TextView tv_des;
    private Button btnok;
    private Button btncancel;

    private ViewLogin viewLogin;

    public Dialog_DK(@NonNull Context context, ViewLogin viewLogin) {
        super(context, R.style.AppThemeDialog);
        this.mContext = context;
        this.viewLogin = viewLogin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_dk);
        initView();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_des = (TextView) findViewById(R.id.tv_des);
        btnok = (Button) findViewById(R.id.btnok);
        btncancel = (Button) findViewById(R.id.btncancel);
        tv_title.setText(mContext.getResources().getString(R.string.titledialog_dk));
        tv_des.setText(mContext.getResources().getString(R.string.desdialog_dk));

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //interface
                viewLogin.changeFramViewLoginSuccess();
                dismiss();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
