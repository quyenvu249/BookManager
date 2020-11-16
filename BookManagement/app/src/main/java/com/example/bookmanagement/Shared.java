package com.example.bookmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.bookmanagement.activity.LoginActivity;

public class Shared {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    String fileName = "userfile";
    String SHARE_PREFFERENCES_NAME = "USER";
    String data = "b";

    public Shared(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARE_PREFFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void secondTime() {
        editor.putBoolean(data, true);
        editor.commit();
    }

    public void firstTime() {
        if (!this.login()) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    private boolean login() {
        return sharedPreferences.getBoolean(data, false);
    }
}
