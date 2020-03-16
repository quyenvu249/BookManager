package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookmanager.R;
import com.example.bookmanager.dao.BookDAO;
import com.example.bookmanager.dao.CategoryDAO;
import com.example.bookmanager.model.Book;
import com.example.bookmanager.model.Category;

import java.util.List;

public class BookActivity extends AppCompatActivity {
    EditText edBookID, edBookName, edBookAuth, edBookNXB, edBookPrice, edBookCount;
    Spinner spinner;
    BookDAO bookDAO;
    CategoryDAO cateDAO;
    List<Category> cateList;
    Button btnAddBook, btnCancelBook, btnListBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        bookDAO = new BookDAO(this);
        cateDAO = new CategoryDAO(this);
        edBookID = findViewById(R.id.edBookID);
        spinner = findViewById(R.id.spinner);
        edBookName = findViewById(R.id.edBookName);
        edBookAuth = findViewById(R.id.edBookAuth);
        edBookNXB = findViewById(R.id.edBookNXB);
        edBookPrice = findViewById(R.id.edBookPrice);
        edBookCount = findViewById(R.id.edBookCount);
        btnAddBook = findViewById(R.id.btnAddBook);
        btnCancelBook = findViewById(R.id.btnCancelBook);
        btnListBook = findViewById(R.id.btnAddBook);
        btnCancelBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edBookID.setText("");
                edBookName.setText("");
                edBookAuth.setText("");
                edBookNXB.setText("");
                edBookPrice.setText("");
                edBookCount.setText("");
            }
        });
        cateList = cateDAO.getListCate();
        ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cateList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        //
        try {
            Intent intent = getIntent();
            Bundle b = intent.getBundleExtra("bunBook");
            edBookID.setText(b.getString("bookID"));
            edBookName.setText(b.getString("bookName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBook(View view) {
        bookDAO = new BookDAO(BookActivity.this);
        Book book = new Book(edBookID.getText().toString(), String.valueOf(spinner.getSelectedItem()), edBookName.getText().toString(), edBookAuth.getText().toString(), edBookNXB.getText().toString(), Double.parseDouble(edBookPrice.getText().toString()), Integer.parseInt(edBookCount.getText().toString()));
        try {
            if (bookDAO.insertBook(book) > 0) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Lỗi", e.toString());
        }


    }

    public void listBook(View view) {
        Intent intent = new Intent(BookActivity.this, ListBookActivity.class);
        startActivity(intent);
    }

    public void updateBook(View view) {
        bookDAO = new BookDAO(this);
        // lấy về người dùng cần update
        Book book = new Book(edBookID.getText().toString(), String.valueOf(spinner.getSelectedItem()), edBookName.getText().toString(), edBookAuth.getText().toString(), edBookNXB.getText().toString(), Double.parseDouble(edBookPrice.getText().toString()), Integer.parseInt(edBookCount.getText().toString()));
        //thực hiện update lên db
        if (bookDAO.updateBook(book.getBookID(), book.getBookName(), book.getBookAut(), book.getBookNXB(), book.getBookPrice(), book.getBookCount()) == 1) {
            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
        }

    }

}
