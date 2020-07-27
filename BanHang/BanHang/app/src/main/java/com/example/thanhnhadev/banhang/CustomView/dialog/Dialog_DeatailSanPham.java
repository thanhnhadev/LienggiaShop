package com.example.thanhnhadev.banhang.CustomView.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thanhnhadev.banhang.R;
import com.example.thanhnhadev.banhang.object.San_pham;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import br.com.felix.imagezoom.ImageZoom;

public class Dialog_DeatailSanPham extends Dialog {
    private ImageView image_view;
    private San_pham sp;
    private TextView tv_detaiTenSP;
    private TextView infor;
    private TextView price;
    public Dialog_DeatailSanPham(@NonNull Context context, San_pham sanPham) {
        super(context, R.style.AppThemeDialog);
        this.sp = sanPham;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_detailsanpham);

        initView();
    }

    private void initView() {
        //get từ trang San_pham ra
        //get hình ảnh
        image_view = (ImageView) findViewById(R.id.image_view);
        byte[] foodImage = sp.getImage();
        android.graphics.Bitmap bitmap = android.graphics.BitmapFactory.decodeByteArray(foodImage,0,foodImage.length);
        image_view.setImageBitmap(bitmap);
        //get ten san pham
        tv_detaiTenSP = (TextView) findViewById(R.id.tv_detaiTenSP);
        tv_detaiTenSP.setText(sp.getTensp());
//        get thong tin sp
        infor = (TextView) findViewById(R.id.infor);
        infor.setText(sp.getDiretion());
        //get giá san pham
        price = (TextView) findViewById(R.id.price);
        price.setText(sp.getGia()+ "");
    }
}
