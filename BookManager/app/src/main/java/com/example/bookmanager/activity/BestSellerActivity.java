package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bookmanager.R;
import com.example.bookmanager.adapter.BookAdapter;
import com.example.bookmanager.dao.BookDAO;
import com.example.bookmanager.model.Book;

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
        setContentView(R.layout.activity_best_seller);
        edtThang = findViewById(R.id.edMonth);
        lvList = findViewById(R.id.lvListBestSeller);
    }

    public void viewListTop(View view) {
        bookDAO = new BookDAO(this);
        list = bookDAO.getSachTop10(edtThang.getText().toString());
        bookAdapter = new BookAdapter(this, list);
        lvList.setAdapter(bookAdapter);
    }
}
