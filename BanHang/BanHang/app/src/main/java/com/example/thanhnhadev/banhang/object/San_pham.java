package com.example.thanhnhadev.banhang.object;

import android.media.Image;

public class San_pham {
    int id;
    String tensp;
    String description;
//    int hinh;
    int gia;
    private byte[] image;


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public San_pham(String tensp, String Diretion, byte[] hinh, int gia) {
        this.description = Diretion;
        this.tensp = tensp;
        this.image = hinh;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getDiretion() {
        return description;
    }

    public void setDiretion(String diretion) {
        description = diretion;
    }

//    public int getHinh() {
//        return hinh;
//    }
//
//    public void setHinh(int hinh) {
//        this.hinh = hinh;
//    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
