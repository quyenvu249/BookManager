package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bookmanager.R;
import com.example.bookmanager.dao.DetailBillDAO;

public class TotalAcivity extends AppCompatActivity {
    TextView tvNgay, tvThang, tvNam;
    DetailBillDAO detailBillDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_acivity);
        detailBillDAO=new DetailBillDAO(this);
        tvNgay = findViewById(R.id.tvNgay);
        tvNgay.setText("Hôm nay: "+detailBillDAO.getDoanhThuTheoNgay());
    }
}
