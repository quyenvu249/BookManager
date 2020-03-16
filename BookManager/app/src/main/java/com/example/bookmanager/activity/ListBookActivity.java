package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bookmanager.R;
import com.example.bookmanager.adapter.BookAdapter;
import com.example.bookmanager.adapter.BookRecAdapter;
import com.example.bookmanager.dao.BookDAO;
import com.example.bookmanager.model.Book;

import java.util.List;

public class ListBookActivity extends AppCompatActivity {
    BookDAO bookDAO;
    //RecyclerView recyclerView;
    ListView lvListBook;
    ImageView imgAdd;
    //BookRecAdapter adapter;
    BookAdapter adapter;
    List<Book> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);
        //recyclerView = findViewById(R.id.rcViewBook);
        lvListBook = (ListView) findViewById(R.id.lvListBook);
        imgAdd = findViewById(R.id.imgAddBook);
        bookDAO = new BookDAO(this);
        list = bookDAO.getListBook();
        adapter = new BookAdapter(this, list);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lvListBook.setAdapter(adapter);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListBookActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });

    }
}
