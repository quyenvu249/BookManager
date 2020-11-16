package com.example.bookmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.activity.UserActivity;
import com.example.bookmanagement.dao.UserDAO;
import com.example.bookmanagement.model.User;

import java.util.List;

public class UserRecViewAdapter extends RecyclerView.Adapter<UserRecViewAdapter.RecyclerViewHolder> {
    private Context context;
    private List<User> arrUser;
    public UserDAO userDAO;
    LayoutInflater inflater;

    public UserRecViewAdapter(Context context, List<User> arrUser) {
        this.context = context;
        this.userDAO=new UserDAO(context);
        this.arrUser = arrUser;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvPhone;
        ImageView ivDelete;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            this.ivIcon = ivIcon;
            this.tvName = tvName;
            this.tvPhone = tvPhone;
            this.ivDelete = ivDelete;
        }
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int i) {
        View viewXML = inflater.inflate(R.layout.item_list_user, null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(viewXML);
        viewHolder.ivIcon = (ImageView) viewXML.findViewById(R.id.imgUser);
        viewHolder.tvName = (TextView) viewXML.findViewById(R.id.tvUsername);
        viewHolder.tvPhone = (TextView) viewXML.findViewById(R.id.tvPhoneNumber);
        viewHolder.ivDelete = (ImageView) viewXML.findViewById(R.id.imgDeleteUser);
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = arrUser.get(i).getUserName();
                userDAO.deleteUser(userName);
                User user = arrUser.get(i);
                arrUser.remove(user);
                notifyDataSetChanged();
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int i) {
        final User user = arrUser.get(i);
        holder.tvName.setText(user.getUserName());
        holder.tvPhone.setText(user.getPhoneNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //context (activity) và du lieu
                    Context context = v.getContext();
                    Intent intent=new Intent(context, UserActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("userName",arrUser.get(i).getUserName());
                    bundle.putString("phoneNumber",arrUser.get(i).getPhoneNumber());
                    bundle.putString("fullName",arrUser.get(i).getFullName());
                    intent.putExtra("bundle",bundle);
                    context.startActivity(intent);
                } catch (Exception e){
                    Log.e("Lỗi",user.getUserName());
                }


            }
        });
    }


    @Override
    public int getItemCount() {
        return arrUser.size();
    }
}
