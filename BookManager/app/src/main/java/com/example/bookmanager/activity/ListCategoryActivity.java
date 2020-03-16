package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bookmanager.R;
import com.example.bookmanager.adapter.CategoryAdapter;
import com.example.bookmanager.adapter.CategoryRecAdapter;
import com.example.bookmanager.dao.CategoryDAO;
import com.example.bookmanager.model.Category;

import java.util.List;

public class ListCategoryActivity extends AppCompatActivity {
    CategoryDAO cateDAO;
    //    CategoryRecAdapter adapter;
//    RecyclerView rcViewCate;
    List<Category> list;
    ListView lvList;
    CategoryAdapter adapter;
    ImageView imgDeleteCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        imgDeleteCate = findViewById(R.id.imgDeleteCate);
        //rcViewCate = findViewById(R.id.rcView);
        lvList = findViewById(R.id.lvListCategory);
        cateDAO = new CategoryDAO(ListCategoryActivity.this);
        list = cateDAO.getListCate();
        adapter = new CategoryAdapter(ListCategoryActivity.this, list);
//        rcViewCate.setAdapter(adapter);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        rcViewCate.setLayoutManager(linearLayoutManager);
        lvList.setAdapter(adapter);
    }

    public void deleteCate(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListCategoryActivity.this);
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
        Intent intent = new Intent(ListCategoryActivity.this, CategoryActivity.class);
        startActivity(intent);
    }
}
