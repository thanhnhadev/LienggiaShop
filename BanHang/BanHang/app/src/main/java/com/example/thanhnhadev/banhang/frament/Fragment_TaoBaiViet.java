package com.example.thanhnhadev.banhang.frament;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thanhnhadev.banhang.Database.DBHelper;
import com.example.thanhnhadev.banhang.MainActivity;
import com.example.thanhnhadev.banhang.R;
import com.example.thanhnhadev.banhang.interfaceView.ViewMain;
import com.example.thanhnhadev.banhang.object.San_pham;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Fragment_TaoBaiViet extends Fragment {
    private ImageView bt_chonhinh;
    private EditText et_title;
    private EditText infor;
    private EditText price;
    private Button btn_ok;
    ViewMain viewMain;
    private List<San_pham> liSP = new ArrayList<>();

    public Fragment_TaoBaiViet() {
        super();
    }

    @SuppressLint("ValidFragment")
    public Fragment_TaoBaiViet(ViewMain viewMain) {
        this.viewMain = viewMain;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fagment_imageview, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        bt_chonhinh = (ImageView) view.findViewById(R.id.bt_chonhinh);
        et_title = (EditText) view.findViewById(R.id.title);
        infor = (EditText) view.findViewById(R.id.infor);
        price = (EditText) view.findViewById(R.id.price);
        btn_ok = (Button) view.findViewById(R.id.btn_ok);

        bt_chonhinh.setOnClickListener(onClick);
        btn_ok.setOnClickListener(onClick);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_chonhinh:
                    viewMain.RequestFormission();
                    break;
                case R.id.btn_ok:
                    insertSanPham();
                    break;

            }
        }
    };
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }
    private void insertSanPham() {
        DBHelper dbHelper = new DBHelper(getContext());
        String title = et_title.getText().toString();
        String inforq = infor.getText().toString();
        int priceq = Integer.parseInt(price.getText().toString());
        byte[] img = imageViewToByte(bt_chonhinh);

        liSP.add(new San_pham(title, inforq, img, priceq));
        dbHelper.insertsanpham(liSP);
        Log.i("databaseSP", "San Pham: " + liSP.size());
        
    }

    public ImageView getBt_chonhinh() {
        return bt_chonhinh;
    }

}


