package com.example.thanhnhadev.banhang;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thanhnhadev.banhang.Adapter.MainTabFragmentAdapter;
import com.example.thanhnhadev.banhang.CustomView.CustomViewPager;
import com.example.thanhnhadev.banhang.frament.Fragment_TaoBaiViet;
//import com.example.thanhnhadev.banhang.frament.Fragment_account;
import com.example.thanhnhadev.banhang.frament.Fragment_account;
import com.example.thanhnhadev.banhang.frament.Fragment_doan;
import com.example.thanhnhadev.banhang.frament.Fragment_thongtin;
import com.example.thanhnhadev.banhang.frament.Fragment_sanpham;
import com.example.thanhnhadev.banhang.interfaceView.ViewMain;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewMain {
    private CustomViewPager customViewPager;
    private MainTabFragmentAdapter mainTabFragmentAdapter;
    private Fragment_TaoBaiViet taoBaiViet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    private void initView() {
        customViewPager = (CustomViewPager) findViewById(R.id.customViewPager);
        customViewPager.setPagingEnabled(false);

        List<Fragment> fragmentList = new ArrayList<>();//rong

        Fragment_sanpham fragmentSanpham = new Fragment_sanpham(this);
        fragmentList.add(fragmentSanpham);

        taoBaiViet = new Fragment_TaoBaiViet(this);
        fragmentList.add(taoBaiViet);

        Fragment_account fagment_account = new Fragment_account();
        fragmentList.add(fagment_account);

        Fragment_thongtin fragmentThongtin = new Fragment_thongtin();
        fragmentList.add(fragmentThongtin);

        Fragment_doan fragmentDoan = new Fragment_doan();
        fragmentList.add(fragmentDoan);

        mainTabFragmentAdapter = new MainTabFragmentAdapter(getSupportFragmentManager(), fragmentList);
        customViewPager.setAdapter(mainTabFragmentAdapter);
        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        customViewPager.setCurrentItem(0, false);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.san_pham) {
            customViewPager.setCurrentItem(0, false);


        } else if (id == R.id.baiviet) {
            customViewPager.setCurrentItem(1, false);

        } else if (id == R.id.nav_account) {
            customViewPager.setCurrentItem(2, false);

        }else if (id == R.id.nav_thongtin) {
            customViewPager.setCurrentItem(3, false);

        } else if (id == R.id.doan) {
            customViewPager.setCurrentItem(4, false);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//fargment tạo bài viết
    @Override
    public void RequestFormission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        );
    }
//giới hạn up load từ thư viện local
    int REQUEST_CODE_GALLERY = 999;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(this, "Bạn không có quyền truy cập vị trí tệp!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                taoBaiViet.getBt_chonhinh().setImageBitmap(bitmap);
               // taoBaiViet.getBt_chonhinh().setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
