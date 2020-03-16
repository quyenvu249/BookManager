package com.example.bookmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanager.R;
import com.example.bookmanager.dao.CategoryDAO;
import com.example.bookmanager.model.Category;

import java.util.List;

public class CategoryRecAdapter extends RecyclerView.Adapter<CategoryRecAdapter.RecyclerViewHolder> {
    Context context;
    List<Category> cateList;
    CategoryDAO cateDAO;
    LayoutInflater inflater;

    public CategoryRecAdapter(Context context, List<Category> cateList) {
        this.context = context;
        this.cateList = cateList;
        this.cateDAO = new CategoryDAO(context);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvCateID;
        TextView tvCateName;
        ImageView ivDelete;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            this.ivIcon = ivIcon;
            this.tvCateID = tvCateID;
            this.tvCateName = tvCateName;
            this.ivDelete = ivDelete;
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int i) {
        View viewXML = inflater.inflate(R.layout.item_list_category, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(viewXML);
        viewHolder.ivIcon = (ImageView) viewXML.findViewById(R.id.imgCate);
        viewHolder.tvCateID = (TextView) viewXML.findViewById(R.id.tvCateID);
        viewHolder.tvCateName = (TextView) viewXML.findViewById(R.id.tvCateName);
        viewHolder.ivDelete = (ImageView) viewXML.findViewById(R.id.imgDeleteCate);
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cateID = cateList.get(i).getCateID();
                cateDAO.deleteCate(cateID);
                Category cate = cateList.get(i);
                cateList.remove(cate);
                notifyDataSetChanged();
            }
        });
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cateList.size();
    }
}
