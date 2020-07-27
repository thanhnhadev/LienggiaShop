package com.example.thanhnhadev.banhang.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thanhnhadev.banhang.Database.DBHelper;
import com.example.thanhnhadev.banhang.Layout.Layout_Signup;
import com.example.thanhnhadev.banhang.R;
import com.example.thanhnhadev.banhang.object.Dang_Ky;

public class Fragment_doan extends Fragment {
    DBHelper dbHelper;
    private TextView tvtest;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fagment_doan, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        dbHelper = new DBHelper(getContext());
        tvtest = (TextView) view.findViewById(R.id.tvtest);
//        for (Dang_Ky dangKy : dbHelper.getListDK()) {
//            if (dangKy.getUsername().equals("nha")) {
//                tvtest.setText(dangKy.getEmail());
//            }
//        }
    }


}

