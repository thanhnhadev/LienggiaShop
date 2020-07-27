package com.example.thanhnhadev.banhang.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thanhnhadev.banhang.R;

public class Fragment_thongtin extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fagment_thongtin, container, false);

        initView(view);
//        addView(0);

        return view;
    }

    private void initView(View view) {
    }


}
