package com.example.thanhnhadev.banhang.frament;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanhnhadev.banhang.Adapter.Adaptersanpham;
import com.example.thanhnhadev.banhang.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Search extends Fragment  {
//    private TextView seach;
//    List<String> mAllValues;
//    private ArrayAdapter<String> mAdapter;
//    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragme_search, container, false   );



        initView(view);
        return view;
    }
//    public boolean onQueryTextChange(String newText) {
//        if (newText == null || newText.trim().isEmpty()) {
//            resetSearch();
//            return false;
//        }
//
//        List<String> filteredValues = new ArrayList<String>(mAllValues);
//        for (String value : mAllValues) {
//            if (!value.toLowerCase().contains(newText.toLowerCase())) {
//                filteredValues.remove(value);
//            }
//        }
//
//        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, filteredValues);
//        new ArrayList(mAdapter);
//
//        return false;
//    }
//    public void resetSearch() {
//        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mAllValues);
//        setListAdapter(mAdapter);
//    }

    private void initView(View view) {
//        mAllValues = new ArrayList<>();
//        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mAllValues);
//        setListAdapter(mAdapter);
    }

}
