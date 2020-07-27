package com.example.thanhnhadev.banhang.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.thanhnhadev.banhang.LoginActivity;
import com.example.thanhnhadev.banhang.object.Dang_Ky;
import com.example.thanhnhadev.banhang.object.San_pham;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_banhang";

    //create table dang ky
    public static final String TABLE_INSERTSANPHAM = "tbl_insearchsanpham";
    public static final String TABLE_DANGKY = "tbl_dangky";
    public static final String TABLE_rsert = "tbl_reset";
    //CREATE COLUM DANG KY
    public static final String KEY_ID = "_id";
    public static final String USER_NAME = "username";
    public static final String EMAIL = "email";
    public static final String PASS = "pass";
    public static final String DIEN_THOAI = "dienthoai";
    //COLUM THEM SAN PHAM
    public static final String KEY_IDSP = "id";
    public static final String TENSP = "tensp";
    public static final String DESCRIPTION = "description";
    public static final String HINH = "hinh";
    public static final String GIA = "gia";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_DANGKY = "CREATE TABLE " + TABLE_DANGKY + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_NAME + " TEXT,"
                + PASS + " TEXT,"
                + EMAIL + " TEXT,"
                + DIEN_THOAI + " TEXT)";
        db.execSQL(CREATE_TABLE_DANGKY);

        String CREATE_TABLE_INSERTSANPHAM = "CREATE TABLE " + TABLE_INSERTSANPHAM + "("
                + KEY_IDSP + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TENSP + " TEXT,"
                + DESCRIPTION + " TEXT,"
                + HINH + " INTEGER,"
                + GIA + " TEXT)";
        db.execSQL(CREATE_TABLE_INSERTSANPHAM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DANGKY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSERTSANPHAM);
        onCreate(db);
    }

    public void insertAccount(List<Dang_Ky> listDK) {
        //Mo ket noi den Database
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        for (Dang_Ky dangKy : listDK) {//for each
            ContentValues values = new ContentValues();
//            values.put(KEY_ID, dangKy.getId());
            values.put(USER_NAME, dangKy.getUsername());
            values.put(PASS, dangKy.getMatkhau());
            values.put(EMAIL, dangKy.getEmail());
            values.put(DIEN_THOAI, dangKy.getSodienthoai());
            db.insert(TABLE_DANGKY, null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }


    //PT xoa
    public void delete(int user_id) {
        //Mo ket noi den Database
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBHelper.TABLE_DANGKY, DBHelper.KEY_ID + "=?", new String[]{String.valueOf(user_id)});
        db.close();
    }

    //lay tat ca du lieu trong bang
    public List<Dang_Ky> getListDK() {
        List<Dang_Ky> listDK = new ArrayList<>();//create new list
        String selectQuery = "SELECT  * FROM " + TABLE_DANGKY;
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Dang_Ky dangky = new Dang_Ky((c.getString(c.getColumnIndex(USER_NAME))), (c.getString(c.getColumnIndex(PASS))), (c.getString(c.getColumnIndex(EMAIL))), (c.getString(c.getColumnIndex(DIEN_THOAI))));
                listDK.add(dangky);
            } while (c.moveToNext());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return listDK;
    }

    //Lay 1 du lieu
    public Dang_Ky fetchStudentByID(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_DANGKY, new String[]{DBHelper.KEY_ID, DBHelper.USER_NAME, DBHelper.EMAIL, DBHelper.PASS, DBHelper.DIEN_THOAI}, DBHelper.KEY_ID + "=?", new String[]{String.valueOf(user_id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return new Dang_Ky(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
    }

    public void insertsanpham(List<San_pham> listSp) {
        SQLiteDatabase db = this.getWritableDatabase();//viet bang database
        db.beginTransaction();//
        for (San_pham san_pham : listSp) {//vong lap
            ContentValues values = new ContentValues();
            values.put(TENSP, san_pham.getTensp());
            values.put(DESCRIPTION, san_pham.getDiretion());
            values.put(HINH, san_pham.getImage());
            values.put(GIA, san_pham.getGia());
            db.insert(TABLE_INSERTSANPHAM, null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    //ham get san pham
    public List<San_pham> getSANPHAM() {
        List<San_pham> list_san_pham = new ArrayList<>();//khoi tao mang cho list_san_pham
        String selectQuery = "SELECT  * FROM " + TABLE_INSERTSANPHAM;//selectquerry khoi tao cau lenh trong bang
//        Log.e(LOG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {//dieu kien: tro toi dong dau trong database
            do {//vong lap : duyet het thuoc tinh  cua dong data dau tien
                San_pham san_pham = new San_pham((c.getString(c.getColumnIndex(TENSP))), (c.getString(c.getColumnIndex(DESCRIPTION))), (c.getBlob(c.getColumnIndex(HINH))), (c.getInt(c.getColumnIndex(GIA))));
                list_san_pham.add(san_pham);
            } while (c.moveToNext());
        }
        db.setTransactionSuccessful();//thanh cong
        db.endTransaction();//ket thuc
        return list_san_pham;//tra ve list_san_pham
    }
    //update reset
    public boolean updateItemDK(String passW, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(PASS, passW);
        try {
            //tạo mảng mới
            String[] args = new String[]{email};
            //update bảng dăng ký truyển vào values,email,mảng
            db.update(TABLE_DANGKY, values, EMAIL + "=?", args);
            //thành công
            db.setTransactionSuccessful();
            //ketthuc
            db.endTransaction();
            return true;
        } catch (SQLiteException e) {
            db.close();

            return false;
        }
    }
    public void updateTK(){
        SQLiteDatabase db= this.getWritableDatabase();
        db.beginTransaction();
    }
}
