package com.example.bookmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.activity.BillActivity;
import com.example.bookmanagement.activity.DetailBillActivity;
import com.example.bookmanagement.dao.BillDAO;
import com.example.bookmanagement.model.Bill;

import java.util.List;

public class BillAdapter extends BaseAdapter {
    Context context;
    List<Bill> list;
    BillDAO billDAO;

    public BillAdapter(Context context, List<Bill> list) {
        this.context = context;
        this.list = list;
        billDAO = new BillDAO(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_bill, parent, false);
        TextView tv1 = convertView.findViewById(R.id.tvBillID);
        TextView tv2 = convertView.findViewById(R.id.tvBillDate);
        final Bill bill = (Bill) getItem(position);
        tv1.setText(bill.getBillID());
        tv2.setText(bill.getDate() + "");
        ImageView ivDelete = convertView.findViewById(R.id.imgDelete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String billID = list.get(position).getBillID();
                billDAO.deleteBill(billID);
                Bill bill1 = list.get(position);
                list.remove(bill1);
                notifyDataSetChanged();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailBillActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("billID", bill.getBillID());
                intent.putExtra("bdBill", bundle);
                context.startActivity(intent);
            }
        });
        return convertView;

    }

}
