package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.bookmanager.R;
import com.example.bookmanager.adapter.DetailBillAdapter;
import com.example.bookmanager.dao.DetailBillDAO;
import com.example.bookmanager.model.DetailBill;

import java.util.List;

public class ListDetailBillByID extends AppCompatActivity {
    ListView lvList;
    DetailBillDAO detailBillDAO;
    DetailBillAdapter detailBillAdapter;
    List<DetailBill> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail_bill_by_i_d);
        lvList = findViewById(R.id.lvHoaDonChiTiet);
        detailBillDAO = new DetailBillDAO(this);
        list = detailBillDAO.getListDetailBill();
        detailBillAdapter = new DetailBillAdapter(this, list);
        lvList.setAdapter(detailBillAdapter);

    }
}
