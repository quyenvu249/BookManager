package com.example.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanager.database.Sqlite;
import com.example.bookmanager.model.Bill;
import com.example.bookmanager.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private Sqlite sqLite;
    private SQLiteDatabase sqLiteDatabase;
    public static final String SQL_BILL = "create table Bill(" +
            "billID text primary key, " +
            "date text) ";
    public static final String TABLE_NAME="Bill";
    public static final String TAG="BillDAO";

    //khởi tạo database
    public BillDAO(Context context){
        sqLite=new Sqlite(context);
        sqLiteDatabase=sqLite.getWritableDatabase();
    }
    public int insertBill(Bill bill){
        ContentValues contentValues=new ContentValues();
        contentValues.put("billID", bill.getBillID());
        contentValues.put("date",bill.getDate());
        try{
            if (sqLiteDatabase.insert(TABLE_NAME,null,contentValues)==-1){
                return -1;
            };

        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        return 1;
    }
    public List<Bill> getListBill() {
        List<Bill> list = new ArrayList<>();
        String listBill = "select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(listBill, null);
        if (cursor.moveToFirst()) {
            do {
                Bill bill = new Bill(cursor.getString(cursor.getColumnIndex("billID")), cursor.getString(cursor.getColumnIndex("date")));
                list.add(bill);
            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();

        return list;
    }
    public int deleteBill(String id) {
        int result = sqLiteDatabase.delete(TABLE_NAME, "billID=?", new String[]{id});
        if (result == 0)
            return -1;
        return 1;
    }
}
