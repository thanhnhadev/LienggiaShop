package com.example.thanhnhadev.banhang.object;

public class Dang_Ky {
    private int id;

    private String username;
    private String matkhau;
    private String email;
    private String sodienthoai;

    public Dang_Ky(String ten, String matkhau, String email, String sdt) {
//        this.id = id;
        this.username = ten;
        this.matkhau = matkhau;
        this.email = email;
        this.sodienthoai = sdt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }


}
