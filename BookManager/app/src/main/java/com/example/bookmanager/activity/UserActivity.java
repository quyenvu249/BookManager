package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager.R;
import com.example.bookmanager.dao.UserDAO;
import com.example.bookmanager.model.User;

public class UserActivity extends AppCompatActivity {
    UserDAO userDAO;
    Button btnAddUser, btnCancel, btnViewListUser;
    EditText edUsername, edPassword, edRePassWord, edPhoneNumber, edFullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("Người dùng");
        btnAddUser = findViewById(R.id.btnAddUser);
        btnCancel = findViewById(R.id.btnCancel);
        btnViewListUser = findViewById(R.id.btnViewListUser);
        edUsername=(EditText) findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPasswword);
        edRePassWord = findViewById(R.id.edRePassword);
        edPhoneNumber = findViewById(R.id.edPhoneNumber);
        edFullName = findViewById(R.id.edFullName);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUsername.setText("");
                edPassword.setText("");
                edRePassWord.setText("");
                edPhoneNumber.setText("");
                edFullName.setText("");
            }
        });
        // nhận dữ liệu từ adapter
        try{
            Intent intent = getIntent();
        if (intent != null) { //neu co dl gui den thi giai nen bundle
            Bundle bundle = intent.getBundleExtra("bundle");
            //dien dl tu bundle vao form
            edUsername.setText(bundle.getString("userName"));
            edPassword.setText(bundle.getString("passWord"));
            edPhoneNumber.setText(bundle.getString("phoneNumber"));
            edFullName.setText(bundle.getString("fullName"));
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addUser(View view) {
        userDAO = new UserDAO(UserActivity.this);
        User user = new User(edUsername.getText().toString(), edPassword.getText().toString(), edPhoneNumber.getText().toString(), edFullName.getText().toString());
        try {
            if (userDAO.insertUser(user) > 0) {
                Toast.makeText(UserActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UserActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Lỗi", e.toString());
        }
    }

    public void listUser(View view) {
        Intent intent = new Intent(UserActivity.this, ListUserActivity.class);
        startActivity(intent);
    }

    public void updateUser(View view) {
        userDAO = new UserDAO(UserActivity.this);
        // lấy về người dùng cần update
        User user = new User(edUsername.getText().toString(), edPassword.getText().toString(), edPhoneNumber.getText().toString(), edFullName.getText().toString());
        //thực hiện update lên db
        if (userDAO.updateUser(user.getUserName(), user.getPhoneNumber(), user.getFullName()) == 1) {
            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
