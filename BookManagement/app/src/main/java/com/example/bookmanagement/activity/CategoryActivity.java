package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.adapter.CategoryAdapter;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.model.Category;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    CategoryDAO cateDAO;
    List<Category> list;
    ListView lvList;
    CategoryAdapter adapter;
    ImageView imgDeleteCate, icAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Category");
        setContentView(R.layout.activity_category);
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
        imgDeleteCate = findViewById(R.id.imgDeleteCate);
        icAdd = findViewById(R.id.icAdd);
        icAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, AddCategoryActivity.class);
                startActivity(intent);
            }
        });
        lvList = findViewById(R.id.lvListCategory);
        cateDAO = new CategoryDAO(CategoryActivity.this);
        list = cateDAO.getListCate();
        adapter = new CategoryAdapter(CategoryActivity.this, list);
        lvList.setAdapter(adapter);

    }

    public void deleteCate(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);
        builder.setMessage("Xóa Thể loại?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                String category = list.get(position + 1).getCateID();
                cateDAO.deleteCate(category);
                Category category1 = list.get(position);
                list.remove(category1);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void addCategory2(View view) {
        Intent intent = new Intent(CategoryActivity.this, AddCategoryActivity.class);
        startActivity(intent);
    }
}
