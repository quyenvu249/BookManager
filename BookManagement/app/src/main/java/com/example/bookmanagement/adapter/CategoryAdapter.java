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
import com.example.bookmanagement.activity.CategoryActivity;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.model.Category;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    Context context;
    List<Category> list;
    CategoryDAO categoryDAO;

    public CategoryAdapter(Context context, List<Category> list) {
        this.context = context;
        this.list = list;
        categoryDAO=new CategoryDAO(context);
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
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_category, parent, false);
        TextView tvCateID = convertView.findViewById(R.id.tvCateID);
        TextView tvCateName = convertView.findViewById(R.id.tvCateName);
        final Category category=(Category)getItem(position);
        tvCateID.setText(category.getCateID());
        tvCateName.setText(category.getCateName());
        ImageView ivDelete =convertView.findViewById(R.id.imgDeleteCate);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cateID = list.get(position).getCateID();
                categoryDAO.deleteCate(cateID);
                Category category1 = list.get(position);
                list.remove(category1);
                notifyDataSetChanged();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                Intent intent = new Intent(context, CategoryActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("cateID",category.getCateID());
                bundle.putString("cateName",category.getCateName());
                intent.putExtra("bdCate",bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
