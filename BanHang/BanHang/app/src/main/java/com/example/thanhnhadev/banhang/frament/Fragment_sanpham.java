package com.example.thanhnhadev.banhang.frament;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.thanhnhadev.banhang.Adapter.Adaptersanpham;
import com.example.thanhnhadev.banhang.CustomView.dialog.Dialog_DK;
import com.example.thanhnhadev.banhang.CustomView.dialog.Dialog_DeatailSanPham;
import com.example.thanhnhadev.banhang.Database.DBHelper;
import com.example.thanhnhadev.banhang.Layout.Layout_Signup;
import com.example.thanhnhadev.banhang.R;
import com.example.thanhnhadev.banhang.interfaceView.ViewMain;
import com.example.thanhnhadev.banhang.interfaceView.ViewSanPham;
import com.example.thanhnhadev.banhang.object.San_pham;

import java.util.ArrayList;
import java.util.List;

public class Fragment_sanpham extends Fragment implements ViewSanPham {
    private Context mContext;

    private RecyclerView re_list;
    private Adaptersanpham adaptersanpham;
    private List<San_pham> list;
    private Layout_Signup layout_signup;
    private EditText tiemkiem;

    public Fragment_sanpham() {
        super();
    }

    @SuppressLint("ValidFragment")
    public Fragment_sanpham(Context mContext ) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragme_sanpham, container, false);
        mContext = getContext().getApplicationContext();
        initView(view);


        loadData();
        return view;
    }

    private void initView(View v) {
        re_list = (RecyclerView) v.findViewById(R.id.re_list);
        tiemkiem = (EditText)v.findViewById(R.id.tiemkiem);

        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(mContext);
        re_list.setLayoutManager(mLayoutmanager);
        re_list.setItemAnimator(new DefaultItemAnimator());


        tiemkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //web api
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptersanpham.getFilter().filter(s);
                //web api
            }

            @Override
            public void afterTextChanged(Editable s) {
             if(tiemkiem.length()==0){
                 //đưa dữ liệu lên
                 loadData();
             }

            }
        });

    }

    private void loadData() {
        DBHelper dbHelper = new DBHelper(getContext());
        list = new ArrayList<>();//tạo mới mảng list
        list = dbHelper.getSANPHAM();//đọc mảng từ database
        if (list == null) {
        } else {
            //cho 1 adaptersanpham truyền vào mContext,list,this
            adaptersanpham = new Adaptersanpham(mContext, list, this);
            //list sp lên recycalview
            re_list.setAdapter(adaptersanpham);
        }
    }


    @Override
    public void showDialogDetailSanPham(San_pham sanPham) {
        //tạo mới Dialog_DeatailSanPham
        Dialog_DeatailSanPham deatailSanPham = new Dialog_DeatailSanPham(getActivity(), sanPham);
        deatailSanPham.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //show từng Dialog_DeatailSanPham lên
        deatailSanPham.show();
    }
}
