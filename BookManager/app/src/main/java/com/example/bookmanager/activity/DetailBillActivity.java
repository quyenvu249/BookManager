package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanager.R;
import com.example.bookmanager.dao.DetailBillDAO;
import com.example.bookmanager.model.DetailBill;

public class DetailBillActivity extends AppCompatActivity {
    DetailBillDAO detailBillDAO;
    EditText edBillID, edBookID, edCount, edDetailBillID;
    TextView tvThanhTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bill);
        edDetailBillID = findViewById(R.id.edMaHoaDonChiTiet);
        edBillID = findViewById(R.id.edMaHoaDon);
        edBookID = findViewById(R.id.edMaSach);
        edCount = findViewById(R.id.edSoLuongMua);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bd");
        edBillID.setText(bundle.getString("billID"));
    }

    public void ADDHoaDonCHITIET(View view) {
        detailBillDAO = new DetailBillDAO(getApplicationContext());
        DetailBill detailBill = new DetailBill(edDetailBillID.getText().toString(), edBillID.getText().toString(), edBookID.getText().toString(), Integer.parseInt(edCount.getText().toString()));
        try {
            if (check() < 0) {
                Toast.makeText(getApplicationContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                if (detailBillDAO.insertDetailBill(detailBill) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Log.e("Lỗi", e.toString());
        };
    }

    public int check() {
        if (edDetailBillID.getText().toString().isEmpty() || edBookID.getText().toString().isEmpty() || edBookID.getText().toString().isEmpty() || edCount.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }

    public void thanhToanHoaDon(View view) {
        double tien = detailBillDAO.tinhtien();
        tvThanhTien.setText(String.valueOf(tien));
    }

    public void listDetailBill(View view) {
        Intent intent = new Intent(this, ListDetailBillByID.class);
        startActivity(intent);
    }
}
