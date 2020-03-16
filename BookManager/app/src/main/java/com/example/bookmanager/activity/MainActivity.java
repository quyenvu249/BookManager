package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bookmanager.R;

public class MainActivity extends AppCompatActivity {
    ImageView ivUser, ivCategory, ivBook, ivBill, ivBestSeller, ivTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Quản lý sách");
        ivUser=findViewById(R.id.ivUser);
        ivCategory=findViewById(R.id.ivCategory);
        ivBook=findViewById(R.id.ivBook);
        ivBill=findViewById(R.id.ivBill);
        ivBestSeller=findViewById(R.id.ivBestSeller);
        ivTotal=findViewById(R.id.ivTotal);
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
        ivCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        ivBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });
        ivBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, BillActivity.class);
                startActivity(intent);
            }
        });
        ivBestSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, BestSellerActivity.class);
                startActivity(intent);
            }
        });
        ivTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TotalAcivity.class);
                startActivity(intent);
            }
        });

    }

}
