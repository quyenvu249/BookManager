package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagement.database.Sqlite;
import com.example.bookmanagement.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Sqlite sqLite;
    private SQLiteDatabase sqLiteDatabase;
    public static final String SQL_BOOK = "create table Book(" +
            "bookID text primary key, " +
            "bookType text, " +
            "bookName text, " +
            "bookAut text," +
            "bookNXB text," +
            "bookPrice double, " +
            "bookCount int )";
    public static final String TABLE_NAME = "Book";
    public static final String TAG = "BookDAO";

    //khởi tạo database
    public BookDAO(Context context) {
        sqLite = new Sqlite(context);
        sqLiteDatabase = sqLite.getWritableDatabase();
    }

    public int insertBook(Book book) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookID", book.getBookID());
        contentValues.put("bookType", book.getBookType());
        contentValues.put("bookName", book.getBookName());
        contentValues.put("bookAut", book.getBookAut());
        contentValues.put("bookNXB", book.getBookNXB());
        contentValues.put("bookPrice", book.getBookPrice());
        contentValues.put("bookCount", book.getBookCount());
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

    public List<Book> getListBook() {
        List<Book> list = new ArrayList<>();
        String listBook = "select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(listBook, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book(cursor.getString(cursor.getColumnIndex("bookID")), cursor.getString(cursor.getColumnIndex("bookType")), cursor.getString(cursor.getColumnIndex("bookName")), cursor.getString(cursor.getColumnIndex("bookAut")), cursor.getString(cursor.getColumnIndex("bookNXB")), cursor.getDouble(cursor.getColumnIndex("bookPrice")), cursor.getInt(cursor.getColumnIndex("bookCount")));
                list.add(book);
            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();

        return list;
    }

    public int deleteBook(String id) {
        int result = sqLiteDatabase.delete(TABLE_NAME, "bookID=?", new String[]{id});
        if (result == 0)
            return -1;
        return 1;
    }

    public List<Book> getBookTop10(String month) {
        List<Book> books = new ArrayList<>();
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String sSQL = "select DetailBill.bookID,Bill.date, sum(account) as soLuong " +
                "from DetailBill INNER JOIN Bill on Bill.billID = DetailBill.billID  " +
                "inner join Book on Book.bookID=DetailBill.bookID " +
               "where strftime('%m',Bill.date)=" + month + "  " +
                "GROUP BY strftime('%m',Bill.date), DetailBill.bookID ORDER BY account DESC limit 10";
        Cursor c = sqLiteDatabase.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Log.d("//====", c.toString());
            Book book = new Book();
            book.setBookID(c.getString(0));
            book.setBookType(c.getString(1));
            book.setBookName(c.getString(2));
            book.setBookAut(c.getString(3));
            book.setBookNXB(c.getString(4));
            book.setBookPrice(c.getDouble(5));
            book.setBookCount(c.getInt(6));
            c.moveToNext();
        }
        c.close();
        return books;
    }

    public int updateBook(String bookID, String bookName, String bookAuth, String bookNXB, double bookPrice, int bookCount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookName", bookName);
        contentValues.put("bookAuth", bookAuth);
        contentValues.put("bookNXB", bookNXB);
        contentValues.put("bookPrice", bookPrice);
        contentValues.put("bookCount", bookCount);
        int rs = sqLiteDatabase.update(TABLE_NAME, contentValues, "bookID=?", new String[]{bookID});
        if (rs == 0)
            return -1;
        return 1;
    }

    public Book getBookByID(String bookID) {
        Book s = null;
        //WHERE clause
        String selection = "bookID=?";
        //WHERE clause arguments
        String[] selectionArgs = {bookID};
        Cursor c = sqLiteDatabase.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Log.d("getBookByID", "" + c.getCount());
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            s = new Book();
            s.setBookID(c.getString(0));
            s.setBookType(c.getString(1));
            s.setBookName(c.getString(2));
            s.setBookAut(c.getString(3));
            s.setBookNXB(c.getString(4));
            s.setBookPrice(c.getDouble(5));
            s.setBookCount(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }
}
