package com.example.bookmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookmanager.R;
import com.example.bookmanager.dao.DetailBillDAO;
import com.example.bookmanager.model.Book;
import com.example.bookmanager.model.DetailBill;

import java.util.List;

public class DetailBillAdapter extends BaseAdapter {
    Context context;
    List<DetailBill> list;
    DetailBillDAO detailBillDAO;

    public DetailBillAdapter(Context context, List<DetailBill> list) {
        this.context = context;
        this.list = list;
        detailBillDAO = new DetailBillDAO(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_detailbill, parent, false);
        TextView tvMaSach = convertView.findViewById(R.id.tvMaSach);
        TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
        TextView tvGiaBia = convertView.findViewById(R.id.tvGiaBia);
        TextView tvThanhTien = convertView.findViewById(R.id.tvThanhTien);
        DetailBill detailBill=(DetailBill) getItem(position);
        Book book=new Book();
        tvMaSach.setText(detailBill.getBookID());
        tvSoLuong.setText(detailBill.getAccount());
        tvGiaBia.setText(String.valueOf(book.getBookPrice()));
        tvThanhTien.setText(String.valueOf(detailBill.getAccount()*book.getBookPrice()));
        return convertView;
    }
}
