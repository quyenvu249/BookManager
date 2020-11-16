package com.example.bookmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.BillDAO;
import com.example.bookmanagement.model.Bill;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddBillActivity extends AppCompatActivity {
    EditText edBillID, edDate;
    DatePicker datePicker;
    Button btnPicDate,btnAdd;
    BillDAO billDAO;
    LinearLayout layout;
    ConstraintLayout root;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Bill");
        setContentView(R.layout.activity_add_bill);
        edBillID = findViewById(R.id.edBillID);
        edDate = findViewById(R.id.edDate);
        btnPicDate=findViewById(R.id.btnPicDate);
        root=findViewById(R.id.root);

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
        btnPicDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker1();
            }
        });
        try {
            Intent intent = getIntent();
            Bundle b = intent.getBundleExtra("bdBill");
            edBillID.setText(b.getString("billID"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void datePicker1() {
        Calendar calendar1 = Calendar.getInstance();
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH);
        final int day = calendar1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar1.set(year, month, dayOfMonth);
                edDate.setText(sdf.format(calendar1.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void addBill(View view) {
        billDAO = new BillDAO(AddBillActivity.this);
        Bill bill = new Bill(edBillID.getText().toString(), edDate.getText().toString());
        try {
            if (edBillID.getText().toString().isEmpty() || edDate.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                if (billDAO.insertBill(bill) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddBillActivity.this, AddDetailBillActivity.class);
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

    public void updateBill(View view) {
        billDAO = new BillDAO(this);
        // lấy về người dùng cần update
        Bill bill = new Bill(edBillID.getText().toString(), edDate.getText().toString());
        //thực hiện update lên db
        if (billDAO.updateBill(bill.getBillID(), bill.getDate()) == 1) {
            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
        }
    }


    public void listBill(View view) {
        Intent intent = new Intent(AddBillActivity.this, BillActivity.class);
        startActivity(intent);
    }
}