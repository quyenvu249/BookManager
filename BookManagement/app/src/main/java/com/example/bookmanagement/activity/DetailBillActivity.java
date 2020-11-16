package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.adapter.DetailBillAdapter;
import com.example.bookmanagement.dao.DetailBillDAO;
import com.example.bookmanagement.model.DetailBill;

import java.util.List;

public class DetailBillActivity extends AppCompatActivity {
    ListView lvList;
    DetailBillDAO detailBillDAO;
    DetailBillAdapter detailBillAdapter;
    List<DetailBill> list;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bill);
        lvList = findViewById(R.id.lvHoaDonChiTiet);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bdBill");
        detailBillDAO = new DetailBillDAO(this);
        list = detailBillDAO.getListDetailBillbyID(bundle.getString("billID"));
        detailBillAdapter = new DetailBillAdapter(this, list);
        lvList.setAdapter(detailBillAdapter);
    }
}