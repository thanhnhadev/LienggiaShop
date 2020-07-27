package com.example.thanhnhadev.banhang;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import android.content.SharedPreferences.Editor;

import com.example.thanhnhadev.banhang.CustomView.dialog.Dialog_DK;
import com.example.thanhnhadev.banhang.Database.DBHelper;
import com.example.thanhnhadev.banhang.interfaceView.ViewLogin;
import com.example.thanhnhadev.banhang.object.Dang_Ky;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ViewLogin {
    private EditText et_username;
    private EditText et_pass;
    private CheckBox remeber;
    public static final String PREFERENCES_FILE_NAME = "login";
    private Button btn_login;
    private Button btn_signup;
    private Button btn_forgetpass;
    private Button btn_dn;
    private String user_name;
    private String pass;
    private LinearLayout ll_signin;
    private LinearLayout ll_dk;
    private EditText et_tk;
    private EditText et_email;
    private EditText et_pw;
    private EditText et_phone;
    private EditText et_phones;
    //reset
    private String edt_mailUpdate;
    private EditText bet_email;
    //
    private Button btn_call;
    private Button btn_dk;
    ImageView imgtest;
    //view reset
    private LinearLayout ll_reset;
    private List<Dang_Ky> listDK = new ArrayList<>();
    private DBHelper dbHelper;
    private SharedPreferences preferences;

    private Button btn_Reset;
    private EditText edt_mailReset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_actvity);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        dbHelper = new DBHelper(this);

        initView();
    }

    private void initView() {
        ll_signin = (LinearLayout) findViewById(R.id.ll_signin);
        ll_dk = (LinearLayout) findViewById(R.id.ll_dk);
        et_username = (EditText) findViewById(R.id.et_username);
        et_pass = (EditText) findViewById(R.id.et_pass);
        remeber = (CheckBox) findViewById(R.id.remeber);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_forgetpass = (Button) findViewById(R.id.btn_forgetpass);
        btn_dn = (Button) findViewById(R.id.btn_dn);
        btn_Reset = (Button) findViewById(R.id.btn_Reset);
        edt_mailReset = (EditText) findViewById(R.id.edt_mailReset);

        //dang ky
        et_tk = (EditText) findViewById(R.id.et_tk);
        et_email = (EditText) findViewById(R.id.et_email);
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_dk = (Button) findViewById(R.id.btn_dk);
        //quen tai khoan
        ll_reset = (LinearLayout) findViewById(R.id.ll_reset);

//        et_phones = (EditText) findViewById(R.id.et_phones);
        btn_call = (Button) findViewById(R.id.btn_call);
        et_email = (EditText) findViewById(R.id.et_email);

        btn_signup.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_dn.setOnClickListener(this);
        btn_dk.setOnClickListener(this);
        btn_forgetpass.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_Reset.setOnClickListener(this);

        //
        getPreferences();
        remeber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user_name = et_username.getText().toString();
                pass = et_pass.getText().toString();

                if (user_name.length() == 0 && pass.length() == 0)
                {
                    Log.i("checked", "chua nhap user");
                    //không lưu
                    remeber.setChecked(false);
                } else {

                    if (isChecked) {// true nho tai khoan
                        Editor editor = preferences.edit();
                        editor.putString("user_name", user_name);//key and value
                        editor.putString("pass", pass);
                        editor.putBoolean("isCheckSaveLogin", isChecked);//true
                        editor.commit();//xác nhận lưu

                    }

                    else {
//                    isCheckSaveLogin = false;
                        Editor editor = preferences.edit();
                        editor.putString("user_name", "");
                        editor.putString("pass", "");
                        editor.putBoolean("isCheckSaveLogin", isChecked);
                        editor.commit();
                    }
                }
            }
        });
    }
//lấy giá trị
    private void getPreferences() {
        et_username.setText(preferences.getString("user_name", user_name));
        et_pass.setText(preferences.getString("pass", pass));
        if (preferences.getBoolean("isCheckSaveLogin", false)) {
            //được check
            remeber.setChecked(true);
        } else {
            //không được check
            remeber.setChecked(false);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                dangNhap();
                break;
            case R.id.btn_signup:
                showHideFrame(1);
                break;
            case R.id.btn_dn:
                showHideFrame(0);
                break;
            case R.id.btn_dk:
                dangKyAccount();
                break;
            case R.id.btn_call:
                showHideFrame(0);
                break;
            case R.id.btn_forgetpass:
                setQuen();
                showHideFrame(2);
                break;
            case R.id.btn_Reset:
                reset();
                break;
        }
    }

    private void setQuen() {
//        this.quen = quen;
        String email = et_email.getText().toString();
        String sdt = btn_call.getText().toString();
    }

    public void showHideFrame(int mode) {
        if (mode == 0) {
            ll_signin.setVisibility(View.VISIBLE);//hien trang dang nhap
            ll_dk.setVisibility(View.GONE);//trang dang ky close
            ll_reset.setVisibility(View.GONE);
        } else if (mode == 1) {
            ll_signin.setVisibility(View.GONE);//trang dang nhap close
            ll_dk.setVisibility(View.VISIBLE);//trang dang ky open
            ll_reset.setVisibility(View.GONE);
        } else if (mode == 2) {
            ll_reset.setVisibility(View.VISIBLE);
            ll_signin.setVisibility(View.GONE);//trang dang nhap close
            ll_dk.setVisibility(View.GONE);//trang dang ky close
        }
    }


    public void dangKyAccount() {
        String taikhoan = et_tk.getText().toString();
        String pass = et_pw.getText().toString();
        String email = et_email.getText().toString();
        String sdt = et_phone.getText().toString();

        if (taikhoan.isEmpty() || pass.isEmpty()
                || email.isEmpty() || sdt.isEmpty()) {
            Toast toast = Toast.makeText(this, "khong the de trong nhung truong tren", Toast.LENGTH_LONG);
            toast.show();
        } else {
            listDK.add(new Dang_Ky(taikhoan, pass, email, sdt));

            dbHelper.insertAccount(listDK);

            Log.i("database", "dang ky: " + listDK.size());

            Dialog_DK dialogDk = new Dialog_DK(this, this);
            dialogDk.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialogDk.show();
        }
    }

    private void dangNhap() {
        user_name = et_username.getText().toString();
        pass = et_pass.getText().toString();
        if (user_name.isEmpty()) {
            //xuất ra thông báo
            Toast toast = Toast.makeText(this, "User name not null", Toast.LENGTH_LONG);
            toast.show();
        } else if (pass.isEmpty()) {
            //xuất ra thong báo
            Toast toast = Toast.makeText(this, "pass not null", Toast.LENGTH_LONG);
            toast.show();
        } else {
            //tạo mới 1 list
            List<Dang_Ky> list = new ArrayList<>();
            //đọc từ list
            list = dbHelper.getListDK();

            for (Dang_Ky dangKy : list) {//vòng lặp
                //nếu truong user_name,pass co ton tai trong object dangky
                if (user_name.equals(dangKy.getUsername()) && pass.equals(dangKy.getMatkhau())) {
                    //vào trang chính
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                }
            }
        }

    }

    private void reset() {
        List<Dang_Ky> dk = new ArrayList<>();
        edt_mailUpdate = edt_mailReset.getText().toString();
        dk = dbHelper.getListDK();//lay danh sach dang ky
        for (Dang_Ky dangKy : dk) {//vòng lặp
            if (edt_mailUpdate.equals(dangKy.getEmail())) {//neu email nhap vao co trong list dang ky
                //update mật khẩu mặt định 12345
                dbHelper.updateItemDK("12345", edt_mailUpdate);
                break;
            }else{
                Toast toast = Toast.makeText(this,"Email khong ton tai",Toast.LENGTH_SHORT);
                toast.show();
            }
            //xuất log
            Log.d("Resetpass", "reset success!");
        }
        //hiện mật khẩu mặt định cho người dùng
        Toast toast = Toast.makeText(this,"password:12345",Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public void changeFramViewLoginSuccess() {
        showHideFrame(0);
    }

}

























