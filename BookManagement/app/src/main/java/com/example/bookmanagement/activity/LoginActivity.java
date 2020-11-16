package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Shared;

public class LoginActivity extends AppCompatActivity {
    EditText edEmail, edPassword;
    Button btnLogin;
    TextView tvRegister;
    String SHARE_PREFFERENCES_NAME = "USER";
    SharedPreferences sharedPreferences;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                sharedPreferences = getSharedPreferences(SHARE_PREFFERENCES_NAME, MODE_PRIVATE);
                String userDetails = sharedPreferences.getString(email + password + "data", "Email or password is incorrect");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("infor", userDetails);

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Shared shared = new Shared(getApplicationContext());
                shared.secondTime();
            }
        });
    }

    private void init() {
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
    }
}