package com.example.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanager.database.Sqlite;
import com.example.bookmanager.model.Bill;
import com.example.bookmanager.model.Book;
import com.example.bookmanager.model.DetailBill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailBillDAO {
    private Sqlite sqLite;
    private SQLiteDatabase sqLiteDatabase;
    public static final String SQL_DETAILBILL = "create table DetailBill(" +
            "detailBillID text primary key, " +
            "billID text, " +
            "bookID text, " +
            "account text)";
    public static final String TABLE_NAME = "DetailBill";
    public static final String TAG = "DetailBillDAO";
    SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");

    //khởi tạo database
    public DetailBillDAO(Context context) {
        sqLite = new Sqlite(context);
        sqLiteDatabase = sqLite.getWritableDatabase();
    }

    public int insertDetailBill(DetailBill detailBill) {
        ContentValues contentValues = new ContentValues();
        //contentValues.put("detailBillID", detailBill.getDetailBillID());
        contentValues.put("billID", detailBill.getBillID());
        contentValues.put("bookID", detailBill.getBookID());
        contentValues.put("account", detailBill.getAccount());
        try {
            if (sqLiteDatabase.insert(TABLE_NAME, null, contentValues) == -1) {
                return -1;
            }
            ;

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    public List<DetailBill> getListDetailBill() {
        List<DetailBill> list = new ArrayList<>();
        String listDetailBill = "select detailBillID, Bill.billID, Bill.date, Book.bookID, Book.bookName, Book.bookAut, Book.bookNXB, Book.bookPrice, Book.bookCount+ DetailBill.account " +
                " from DetailBill inner join Bill on DetailBill.billID=Bill.billID inner join Book on Book.bookID=DetailBill.bookID ";
        Cursor cursor = sqLiteDatabase.rawQuery(listDetailBill, null);
        if (cursor.moveToFirst()) {
            do {
                DetailBill detailBill = new DetailBill();
                cursor.getString(cursor.getColumnIndex("detailBillID"));
                cursor.getString(cursor.getColumnIndex("billID"));
                cursor.getString(cursor.getColumnIndex("bookID"));
                cursor.getInt(cursor.getColumnIndex("account"));
                list.add(detailBill);
            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();

        return list;

    }

    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String sql = "select  sum(Book.bookPrice *DetailBill.account) as 'tongtien' " +
                "from Bill inner join DetailBill on Bill.billID=DetailBill.billID " +
                "inner join Book on DetailBill.bookID=Book.bookID where Bill.date=date('now')" +
                "group by DetailBill.bookID";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }

    public double tinhtien() {
        double thanhtien = 0;
        Book book = new Book();
        DetailBill detailBill = new DetailBill();
        String sltong = "select (bookPrice*account) as 'thanhtien' from DetailBill inner join Book on Book.bookID=DetailBill.bookID ";
        Cursor cursor = sqLiteDatabase.rawQuery(sltong, null);
        if (cursor.moveToFirst()) {
            do {
                thanhtien = book.getBookPrice() * detailBill.getAccount();
            } while (cursor.moveToNext());
        }

        return thanhtien;
    }


}
