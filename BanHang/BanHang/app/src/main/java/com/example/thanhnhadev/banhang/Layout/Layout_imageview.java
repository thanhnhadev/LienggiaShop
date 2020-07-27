package com.example.thanhnhadev.banhang.Layout;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thanhnhadev.banhang.R;

public class Layout_imageview extends LinearLayout {
//    private static final String DATABASE_NAME = "db_banhang";
//    private  static final  Resquest_Take_Photo = 123;

    private TextView nav_create;
    private ImageButton chonhinh;
    private EditText title,infor,price;
    private Button btn_signup;
    private Intent intent;

    public Layout_imageview(Context context) {
        super(context);
        this.nav_create = nav_create;
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li;
        li = (LayoutInflater)getContext().getSystemService(inflater);
        li.inflate(R.layout.fagment_imageview, this, true);
        Log.i("Layout imageview","layout image ");
//        addControl();
        initUI();
    }

    private void initUI() {
        Intent intent = getIntent();
    }


    public Intent getIntent() {
        return intent;
    }
}
