package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bookmanagement.R;

public class SignupActivity extends AppCompatActivity {
    String SHARE_PREFFERENCES_NAME = "USER";
    EditText edEmail, edPassword, edName;
    Button btnSignup;
    ImageView icBack;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(SHARE_PREFFERENCES_NAME, MODE_PRIVATE);
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String name = edName.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(email + password + "data", email + "/n" + name);
                editor.commit();

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init() {
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        btnSignup = findViewById(R.id.btnSignup);
        edName = findViewById(R.id.edName);
        icBack = findViewById(R.id.icBack);
    }
}