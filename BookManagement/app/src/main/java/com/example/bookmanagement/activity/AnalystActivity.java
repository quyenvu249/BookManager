package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.DetailBillDAO;

public class AnalystActivity extends AppCompatActivity {
    TextView tvNgay, tvThang, tvNam;
    DetailBillDAO detailBillDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Analyst");
        setContentView(R.layout.activity_analyst);
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
        detailBillDAO = new DetailBillDAO(this);
        tvNgay = findViewById(R.id.tvNgay);
        tvThang = findViewById(R.id.tvThang);
        tvNam = findViewById(R.id.tvNam);
        tvNgay.setText("Hôm nay: " + detailBillDAO.getDoanhThuTheoNgay());
//        tvThang.setText("Tháng này: " + detailBillDAO.getDoanhThuTheoThang());
//        tvNam.setText("Năm nay: " + detailBillDAO.getDoanhThuTheoNam());
    }
}