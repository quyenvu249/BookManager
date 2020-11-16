package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.model.Category;

public class AddCategoryActivity extends AppCompatActivity {
    EditText edCateID, edCateName, edCatePosition, edCateDes;
    CategoryDAO cateDAO;
    Button btnAddCate, btnCancelCate, btnListCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add category");
        setContentView(R.layout.activity_add_category);
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
        edCateID = findViewById(R.id.edCateID);
        edCateName = findViewById(R.id.edCateName);
        edCatePosition = findViewById(R.id.edCatePosition);
        edCateDes = findViewById(R.id.edCateDes);
        btnAddCate = findViewById(R.id.btnAddCate);
//        btnCancelCate = findViewById(R.id.btnCancelCate);
//        btnListCate = findViewById(R.id.btnListCate);

        try {
            Intent intent = getIntent();
            Bundle b = intent.getBundleExtra("bdCate");
            edCateID.setText(b.getString("cateID"));
            edCateName.setText(b.getString("cateName"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addCate(View view) {
        cateDAO = new CategoryDAO(this);
        Category cate = new Category(edCateID.getText().toString(), edCateName.getText().toString(), edCatePosition.getText().toString(), edCateDes.getText().toString());
        try {
            if (cateDAO.insertCate(cate) > 0) {
                Toast.makeText(AddCategoryActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddCategoryActivity.this, CategoryActivity.class));
            } else {
                Toast.makeText(AddCategoryActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Lỗi", e.toString());
        }
    }

}