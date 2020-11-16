package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagement.database.Sqlite;
import com.example.bookmanagement.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Sqlite sqLite;
    private SQLiteDatabase sqLiteDatabase;
    public static final String SQL_USER = "create table User(" +
            "userName text primary key, " +
            "passWord text, " +
            "phoneNumber text, " +
            "fullName text)";
    public static final String TABLE_NAME = "User";
    public static final String TAG = "UserDAO";

    //khởi tạo database
    public UserDAO(Context context) {
        sqLite = new Sqlite(context);
        sqLiteDatabase = sqLite.getWritableDatabase();
    }

    public int insertUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", user.getUserName());
        contentValues.put("passWord", user.getPassWord());
        contentValues.put("phoneNumber", user.getPhoneNumber());
        contentValues.put("fullName", user.getFullName());
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

    public List<User> getListUser() {
        List<User> list = new ArrayList<>();
        String listUser = "select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(listUser, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(cursor.getColumnIndex("userName")), cursor.getString(cursor.getColumnIndex("passWord")), cursor.getString(cursor.getColumnIndex("phoneNumber")), cursor.getString(cursor.getColumnIndex("fullName")));
                list.add(user);
            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();

        return list;
    }

    public int deleteUser(String userName) {
        int result = sqLiteDatabase.delete(TABLE_NAME, "userName=?", new String[]{userName});
        if (result == 0)
            return -1;
        return 1;
    }

    public int updateUser(String userName, String phoneNumber, String fullName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phoneNumber", phoneNumber);
        contentValues.put("fullName", fullName);
        int rs = sqLiteDatabase.update(TABLE_NAME, contentValues, "userName=?", new String[]{userName});
        if (rs == 0)
            return -1;
        return 1;
    }
}
