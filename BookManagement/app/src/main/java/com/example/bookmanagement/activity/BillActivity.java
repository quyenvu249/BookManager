package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.adapter.BillAdapter;
import com.example.bookmanagement.dao.BillDAO;
import com.example.bookmanagement.model.Bill;

import java.util.List;

public class BillActivity extends AppCompatActivity {
    ListView listView;
    BillDAO billDAO;
    BillAdapter adapter;
    List<Bill> arrList;
    ImageView icAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Bill");
        setContentView(R.layout.activity_bill);
        listView = findViewById(R.id.lvList);
        icAdd = findViewById(R.id.icAdd);
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
        billDAO = new BillDAO(getApplicationContext());
        arrList = billDAO.getListBill();
        adapter = new BillAdapter(getApplicationContext(), arrList);
        listView.setAdapter(adapter);

        icAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BillActivity.this, AddBillActivity.class));
            }
        });
    }
}