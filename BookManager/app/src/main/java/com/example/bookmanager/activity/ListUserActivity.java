package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bookmanager.R;
import com.example.bookmanager.adapter.UserRecViewAdapter;
import com.example.bookmanager.dao.UserDAO;
import com.example.bookmanager.model.User;

import java.util.List;

public class ListUserActivity extends AppCompatActivity {
    UserDAO userDAO;
    List<User> list;
    //    UserAdapter adapter;
    UserRecViewAdapter adapter;
    //    ListView lvListUser;
    RecyclerView rcView;
    ImageView imgDeleteUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        imgDeleteUser = findViewById(R.id.imgDeleteUser);
//        lvListUser = findViewById(R.id.lvListUser);
        rcView=findViewById(R.id.rcView);
        userDAO = new UserDAO(ListUserActivity.this);
        list = userDAO.getListUser();
        adapter = new UserRecViewAdapter(ListUserActivity.this, list);
//        lvListUser.setAdapter(adapter);
        rcView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcView.setLayoutManager(linearLayoutManager);

    }
    public void addUser2(View view) {
        Intent intent=new Intent(ListUserActivity.this,UserActivity.class);
        startActivity(intent);
    }
}
