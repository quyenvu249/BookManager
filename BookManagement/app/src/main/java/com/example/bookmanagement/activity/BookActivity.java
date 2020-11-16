package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.adapter.BookAdapter;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.model.Book;

import java.util.List;

public class BookActivity extends AppCompatActivity {
    BookDAO bookDAO;
    ListView lvListBook;
    ImageView icAdd;
    BookAdapter adapter;
    List<Book> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Book");
        setContentView(R.layout.activity_book);
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

        icAdd = findViewById(R.id.icAdd);

        lvListBook = (ListView) findViewById(R.id.lvListBook);
        bookDAO = new BookDAO(this);
        list = bookDAO.getListBook();
        adapter = new BookAdapter(this, list);
        lvListBook.setAdapter(adapter);
        icAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

    }
}