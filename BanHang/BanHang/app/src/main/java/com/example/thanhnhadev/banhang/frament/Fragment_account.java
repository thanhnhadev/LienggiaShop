package com.example.thanhnhadev.banhang.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thanhnhadev.banhang.Database.DBHelper;
import com.example.thanhnhadev.banhang.R;

public class Fragment_account extends Fragment{
    DBHelper dbHelper;
    private Button btn_lgout;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fagment_account, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
