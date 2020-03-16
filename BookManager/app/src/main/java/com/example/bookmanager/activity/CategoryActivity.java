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
import com.example.bookmanager.adapter.CategoryRecAdapter;
import com.example.bookmanager.dao.CategoryDAO;
import com.example.bookmanager.model.Category;

public class CategoryActivity extends AppCompatActivity {
    EditText edCateID, edCateName, edCatePosition, edCateDes;
    CategoryDAO cateDAO;
    Button btnAddCate, btnCancelCate, btnListCate;
    CategoryRecAdapter cateRecAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("Thể loại");
        edCateID = findViewById(R.id.edCateID);
        edCateName = findViewById(R.id.edCateName);
        edCatePosition = findViewById(R.id.edCatePosition);
        edCateDes = findViewById(R.id.edCateDes);
        btnAddCate = findViewById(R.id.btnAddCate);
        btnCancelCate = findViewById(R.id.btnCancelCate);
        btnListCate = findViewById(R.id.btnListCate);
        btnCancelCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edCateID.setText("");
                edCateName.setText("");
                edCatePosition.setText("");
                edCateDes.setText("");
            }
        });
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
                Toast.makeText(CategoryActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CategoryActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Lỗi", e.toString());
        }
    }


    public void listCategory(View view) {
        Intent intent = new Intent(CategoryActivity.this, ListCategoryActivity.class);
        startActivity(intent);
    }

    public void updateCate(View view) {
        cateDAO = new CategoryDAO(CategoryActivity.this);
        // lấy về người dùng cần update
        Category category = new Category(edCateID.getText().toString(), edCateName.getText().toString(), edCatePosition.getText().toString(), edCateDes.getText().toString());
        //thực hiện update lên db
        if (cateDAO.updateCate(category.getCateID(), category.getCatePosition(), category.getCateDes()) == 1) {
            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
        }

    }
}
