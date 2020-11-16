package com.example.bookmanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bookmanagement.dao.BillDAO;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.dao.DetailBillDAO;
import com.example.bookmanagement.dao.UserDAO;

public class Sqlite extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DBBook";
    public static final int VERSION = 2;

    public Sqlite(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CategoryDAO.SQL_CATE);
        db.execSQL(BookDAO.SQL_BOOK);
        db.execSQL(BillDAO.SQL_BILL);
        db.execSQL(DetailBillDAO.SQL_DETAILBILL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserDAO.TABLE_NAME);
        db.execSQL("drop table if exists " + CategoryDAO.TABLE_NAME);
        db.execSQL("drop table if exists " + BookDAO.TABLE_NAME);
        db.execSQL("drop table if exists " + BillDAO.TABLE_NAME);
        db.execSQL("drop table if exists " + DetailBillDAO.TABLE_NAME);
    }
}
