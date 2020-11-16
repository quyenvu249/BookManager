package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.adapter.DetailBillAdapter;
import com.example.bookmanagement.dao.BillDAO;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.dao.DetailBillDAO;
import com.example.bookmanagement.model.DetailBill;

import java.util.ArrayList;

public class AddDetailBillActivity extends AppCompatActivity {
    DetailBillDAO detailBillDAO;
    BookDAO bookDAO;
    EditText edBillID, edBookID, edCount, edDetailBillID;
    TextView tvThanhTien;
    BillDAO billDAO;
    ListView lvCart;
    ArrayList<DetailBill> arrayList;
    DetailBillAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail_bill);
        edDetailBillID = findViewById(R.id.edMaHoaDonChiTiet);
        edBillID = findViewById(R.id.edMaHoaDon);
        edBookID = findViewById(R.id.edMaSach);
        edCount = findViewById(R.id.edSoLuongMua);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        lvCart = findViewById(R.id.lvCart);
        arrayList = new ArrayList<>();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bd");
        edBillID.setText(bundle.getString("billID"));
    }

    public void ADDHoaDonCHITIET(View view) {
        detailBillDAO = new DetailBillDAO(getApplicationContext());
        bookDAO = new BookDAO(getApplicationContext());
        billDAO = new BillDAO(getApplicationContext());
        try {
            if (check() < 0) {
                Toast.makeText(getApplicationContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                DetailBill detailBill = new DetailBill(edDetailBillID.getText().toString(), billDAO.getBillByID(edBillID.getText().toString()), bookDAO.getBookByID(edBookID.getText().toString()), Integer.parseInt(edCount.getText().toString()));

                if (detailBillDAO.insertDetailBill(detailBill) > 0) {
                    arrayList.add(detailBill);
                    adapter = new DetailBillAdapter(AddDetailBillActivity.this, arrayList);
                    lvCart.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    // Log.e("tên",detailBill.getDetailBillID()+"");
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Log.e("Lỗi", e.toString());
        }
        ;
    }

    public int check() {
        if (edDetailBillID.getText().toString().isEmpty() || edBookID.getText().toString().isEmpty() || edBookID.getText().toString().isEmpty() || edCount.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }

    public void thanhToanHoaDon(View view) {
        double tinhtien = detailBillDAO.tinhtien();
        tvThanhTien.setText(String.valueOf(tinhtien));
    }

    public void listDetailBill(View view) {
        Intent intent = new Intent(this, DetailBillActivity.class);
        startActivity(intent);
    }
}