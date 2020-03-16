package com.example.bookmanager.activity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager.R;
import com.example.bookmanager.adapter.BillAdapter;
import com.example.bookmanager.dao.BillDAO;
import com.example.bookmanager.model.Bill;

import java.util.List;

public class ListBillActivity extends AppCompatActivity {
    ListView listView;
    BillDAO billDAO;
    BillAdapter adapter;
    List<Bill> arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        listView = findViewById(R.id.lvList);
        billDAO = new BillDAO(getApplicationContext());
        arrList = billDAO.getListBill();
        adapter = new BillAdapter(getApplicationContext(), arrList);
        listView.setAdapter(adapter);

    }
}
