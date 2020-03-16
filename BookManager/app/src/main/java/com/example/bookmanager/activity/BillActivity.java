package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager.R;
import com.example.bookmanager.dao.BillDAO;
import com.example.bookmanager.model.Bill;
import com.example.bookmanager.model.DetailBill;

import java.sql.Date;
import java.util.Calendar;


public class BillActivity extends AppCompatActivity {
    EditText edBillID, edBillDate;
    DatePicker datePicker;
    BillDAO billDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        edBillID = findViewById(R.id.edBillID);
        // edBillDate = findViewById(R.id.edBillDate);
        datePicker = findViewById(R.id.dpPickDate);
        setupDatePicker();
    }

    public void setupDatePicker() {
        Calendar calendar = Calendar.getInstance();
        // Lấy ra năm - tháng - ngày hiện tại
        int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        // Khởi tạo sự kiện lắng nghe khi DatePicker thay đổi
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(BillActivity.this, dayOfMonth + "-" + monthOfYear + "-" + year, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addBill(View view) {
        billDAO = new BillDAO(getApplicationContext());
        String date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();
        Bill bill = new Bill(edBillID.getText().toString(), date);
        try {
            if (edBillID.getText().toString().isEmpty() || date.equals("")) {
                Toast.makeText(getApplicationContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                if (billDAO.insertBill(bill) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BillActivity.this, DetailBillActivity.class);
                    Bundle b = new Bundle();
                    b.putString("billID", edBillID.getText().toString());
                    intent.putExtra("bd", b);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Log.e("Lỗi", e.toString());
        }
    }

    public void listBill(View view) {
        Intent intent = new Intent(BillActivity.this, ListBillActivity.class);
        startActivity(intent);
    }

    public void detailBill(View view) {
        Intent intent = new Intent(BillActivity.this, DetailBillActivity.class);
        startActivity(intent);
    }

//    public void updateCate(View view) {
//        billDAO = new CategoryDAO(CategoryActivity.this);
//        // lấy về người dùng cần update
//        Category category = new Category(edCateID.getText().toString(), edCateName.getText().toString(), edCatePosition.getText().toString(), edCateDes.getText().toString());
//        //thực hiện update lên db
//        if (cateDAO.updateCate(category.getCateID(), category.getCatePosition(), category.getCateDes()) == 1) {
//            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
//        }
//
//    }
}
