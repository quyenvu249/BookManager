package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.adapter.BookAdapter;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.model.Book;

import java.util.List;

public class BestSellerActivity extends AppCompatActivity {
    EditText edtThang;
    BookDAO bookDAO;
    BookAdapter bookAdapter;
    List<Book> list;
    ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Top 10 best seller");
        setContentView(R.layout.activity_best_seller);
        edtThang = findViewById(R.id.edMonth);
        lvList = findViewById(R.id.lvListBestSeller);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void viewListTop(View view) {
        if (Integer.parseInt(edtThang.getText().toString()) > 12 || Integer.parseInt(edtThang.getText().toString()) < 0) {
            Toast.makeText(this, "Nhập đúng định dạng tháng", Toast.LENGTH_LONG).show();
        } else {
            bookDAO = new BookDAO(this);
            list = bookDAO.getBookTop10(edtThang.getText().toString());
            bookAdapter = new BookAdapter(this, list);
            lvList.setAdapter(bookAdapter);
        }
    }
}