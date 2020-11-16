package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagement.database.Sqlite;
import com.example.bookmanagement.model.Bill;
import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.DetailBill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailBillDAO {
    private Sqlite sqLite;
    private SQLiteDatabase sqLiteDatabase;
    public static final String SQL_DETAILBILL = "create table DetailBill(" +
            "detailBillID integer primary key AUTOINCREMENT, " +
            "billID text, " +
            "bookID text, " +
            "account text," +
            "sum double)";
    public static final String TABLE_NAME = "DetailBill";
    public static final String TAG = "DetailBillDAO";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Calendar calendar = Calendar.getInstance();
    String dateToday = sdf.format(calendar.getTime());

    //khởi tạo database
    public DetailBillDAO(Context context) {
        sqLite = new Sqlite(context);
        sqLiteDatabase = sqLite.getWritableDatabase();
    }

    public int insertDetailBill(DetailBill detailBill) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("billID", detailBill.getBill().getBillID());
        contentValues.put("bookID", detailBill.getBook().getBookID());
        contentValues.put("account", detailBill.getAccount());
        try {
            if (sqLiteDatabase.insert(TABLE_NAME, null, contentValues) == -1) {
                return -1;
            }

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    public List<DetailBill> getListDetail() {
        List<DetailBill> list = new ArrayList<>();
        String listDetailBill = "select * from Detailbill";
        Cursor cursor = sqLiteDatabase.rawQuery(listDetailBill, null);
        if (cursor.moveToFirst()) {
            do {
                DetailBill detailBill = new DetailBill();
                detailBill.setDetailBillID(cursor.getString(0));
                detailBill.setBill(new Bill(cursor.getString(1), cursor.getString(2)));
                detailBill.setBook(new Book(cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getDouble(8), cursor.getInt(9)));
                detailBill.setAccount(cursor.getInt(10));
                list.add(detailBill);
            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();
        return list;

    }

    public List<DetailBill> getListDetailBillbyID(String billID) {
        List<DetailBill> list = new ArrayList<>();
        String listDetailBill = "select detailBillID, Bill.billID, Bill.date, Book.bookID,Book.bookType, Book.bookName, Book.bookAut, Book.bookNXB, Book.bookPrice, Book.bookCount+ DetailBill.account " +
                " from DetailBill inner join Bill on DetailBill.billID=Bill.billID inner join Book on Book.bookID=DetailBill.bookID where DetailBill.billID='" + billID + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(listDetailBill, null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    DetailBill detailBill = new DetailBill();
                    detailBill.setDetailBillID(cursor.getString(0));
                    detailBill.setBill(new Bill(cursor.getString(1), cursor.getString(2)));
                    detailBill.setBook(new Book(cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getDouble(8), cursor.getInt(9)));
                    detailBill.setAccount(cursor.getInt(10));
                    list.add(detailBill);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        sqLiteDatabase.close();
        return list;

    }


    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String sql = "SELECT SUM(tongtien) from (select Book.bookPrice * DetailBill.account as 'tongtien' " +
                "FROM DetailBill INNER JOIN Book on DetailBill.bookID = Book.bookID INNER JOIN Bill on DetailBill.billID= Bill.billID where Bill.date =" + dateToday +
                "  GROUP BY DetailBill.bookID)";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                doanhThu += cursor.getDouble(0);
            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();
        return doanhThu;
    }

//    public double getDoanhThuTheoThang() {
//        double doanhThu = 0;
//        String sql = "SELECT SUM(tongtien) from (SELECT SUM(Book.bookPrice * DetailBill.account) as 'tongtien' " +
//                "FROM Bill INNER JOIN DetailBill on Bill.billID = DetailBill.billID " + "INNER JOIN Book on DetailBill.bookID = Book.bookID where strftime('%m',Bill.date) = strftime('%m','now') GROUP BY DetailBill.bookID)tmp";
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast() == false) {
//            doanhThu = cursor.getDouble(0);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return doanhThu;
//    }
//
//    public double getDoanhThuTheoNam() {
//        double doanhThu = 0;
//        String sql = "SELECT SUM(tongtien) from (SELECT SUM(Book.bookPrice * DetailBill.account) as 'tongtien' " +
//                "FROM Bill INNER JOIN DetailBill on Bill.billID = DetailBill.billID " + "INNER JOIN Book on DetailBill.bookID = Book.bookID where strftime('%Y',Bill.date) = strftime('%Y','now') GROUP BY DetailBill.bookID)tmp";
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast() == false) {
//            doanhThu = cursor.getDouble(0);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return doanhThu;
//    }

    public double tinhtien() {
        double thanhtien = 0;
        String slt = "select (Book.bookPrice * DetailBill.account) as 'thanhtien' from DetailBill inner join Book on DetailBill.bookID=Book.bookID ";
        Cursor cursor = sqLiteDatabase.rawQuery(slt, null);
        if (cursor.moveToFirst()) {
            do {
                thanhtien += cursor.getDouble(0);
            } while (cursor.moveToNext());
        }

        return thanhtien;
    }


}
