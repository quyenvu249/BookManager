package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagement.database.Sqlite;
import com.example.bookmanagement.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Sqlite sqLite;
    private SQLiteDatabase sqLiteDatabase;
    public static final String SQL_CATE = "create table Cate(" +
            "cateID text primary key, " +
            "cateName text, " +
            "catePosition text, " +
            "cateDes text)";
    public static final String TABLE_NAME="Cate";
    public static final String TAG="CateDAO";

    //khởi tạo database
    public CategoryDAO(Context context){
        sqLite=new Sqlite(context);
        sqLiteDatabase=sqLite.getWritableDatabase();
    }
    public int insertCate(Category cate){
        ContentValues contentValues=new ContentValues();
        contentValues.put("cateID", cate.getCateID());
        contentValues.put("cateName",cate.getCateName());
        contentValues.put("catePosition",cate.getCatePosition());
        contentValues.put("cateDes",cate.getCateDes());
        try{
            if (sqLiteDatabase.insert(TABLE_NAME,null,contentValues)==-1){
                return -1;
            };

        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        return 1;
    }
    public int deleteCate(String cateID) {
        int result = sqLiteDatabase.delete(TABLE_NAME, "cateID=?", new String[]{cateID});
        if (result == 0)
            return -1;
        return 1;
    }
    public List<Category> getListCate() {
        List<Category> list = new ArrayList<>();
        String listCate = "select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(listCate, null);
        if (cursor.moveToFirst()) {
            do {
                Category cate = new Category(cursor.getString(cursor.getColumnIndex("cateID")), cursor.getString(cursor.getColumnIndex("cateName")), cursor.getString(cursor.getColumnIndex("catePosition")), cursor.getString(cursor.getColumnIndex("cateDes")));
                list.add(cate);
            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();

        return list;
    }
    public int updateCate(String cateID, String catePosition, String cateDes) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("catePosition", catePosition);
        contentValues.put("cateDes", cateDes);
        int rs = sqLiteDatabase.update(TABLE_NAME, contentValues, "cateID=?", new String[]{cateID});
        if (rs == 0)
            return -1;
        return 1;
    }
}
