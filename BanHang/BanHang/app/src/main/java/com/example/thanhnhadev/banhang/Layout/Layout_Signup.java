package com.example.thanhnhadev.banhang.Layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.thanhnhadev.banhang.R;

public class Layout_Signup extends LinearLayout{
    private Button btn_dn;
    public Layout_Signup(Context context) {
        super(context);
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li;
        li = (LayoutInflater)getContext().getSystemService(inflater);
        li.inflate(R.layout.layout_signup, this, true);

        initView();
    }

    private void initView() {
        btn_dn = (Button)findViewById(R.id.btn_dn);

        btn_dn.setOnClickListener(onClick);
    }

    private View.OnClickListener onClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_dn:

                    break;
            }
        }
    };
}
