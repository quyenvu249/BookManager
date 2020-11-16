package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagement.database.Sqlite;
import com.example.bookmanagement.model.Bill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private Sqlite sqLite;
    private SQLiteDatabase sqLiteDatabase;
    public static final String SQL_BILL = "create table Bill(" +
            "billID text primary key, " +
            "date text) ";
    public static final String TABLE_NAME = "Bill";
    public static final String TAG = "BillDAO";
    SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");

    //khởi tạo database
    public BillDAO(Context context) {
        sqLite = new Sqlite(context);
        sqLiteDatabase = sqLite.getWritableDatabase();
    }

    public int insertBill(Bill bill) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("billID", bill.getBillID());
        contentValues.put("date", bill.getDate());
        try {
            if (sqLiteDatabase.insert(TABLE_NAME, null, contentValues) == -1) {
                return -1;
            }

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    public List<Bill> getListBill() {
        List<Bill> list = new ArrayList<>();
        String listBill = "select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(listBill, null);
        if (cursor.moveToFirst()) {
            do {
                Bill bill = null;
                bill = new Bill(cursor.getString(0), cursor.getString(1));
                list.add(bill);
            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();

        return list;
    }

    public int updateBill(String billID, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("billID", billID);
        contentValues.put("date", date);
        int rs = sqLiteDatabase.update(TABLE_NAME, contentValues, "billID=?", new String[]{billID});
        if (rs == 0)
            return -1;
        return 1;
    }

    public int deleteBill(String id) {
        int result = sqLiteDatabase.delete(TABLE_NAME, "billID=?", new String[]{id});
        if (result == 0)
            return -1;
        return 1;
    }

    public Bill getBillByID(String billID) {
        Bill s = null;
        //WHERE clause
        String selection = "billID=?";
        //WHERE clause arguments
        String[] selectionArgs = {billID};
        Cursor c = sqLiteDatabase.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Log.d("getBillByID", "===>" + c.getCount());
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            s = new Bill();
            s.setBillID(c.getString(0));
            s.setDate(c.getString(1));
            break;
        }
        c.close();
        return s;
    }
}
