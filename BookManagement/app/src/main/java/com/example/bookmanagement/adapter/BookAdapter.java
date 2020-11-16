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
import com.example.bookmanagement.activity.BookActivity;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.model.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    Context context;
    List<Book> list;
    BookDAO bookDAO;

    public BookAdapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
        bookDAO =new BookDAO(context);
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
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_book, parent, false);
        TextView tv1 = convertView.findViewById(R.id.tvBookID);
        TextView tv2 = convertView.findViewById(R.id.tvBookName);
        final Book book=(Book) getItem(position);
        tv1.setText(book.getBookID());
        tv2.setText(book.getBookName());
        ImageView ivDelete =convertView.findViewById(R.id.imgDelete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookID = list.get(position).getBookID();
                bookDAO.deleteBook(bookID);
                Book book1 = list.get(position);
                list.remove(book1);
                notifyDataSetChanged();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                Intent intent=new Intent(context, BookActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("bookID",book.getBookID());
                bundle.putString("bookName",book.getBookName());
                intent.putExtra("bunBook",bundle);
                context.startActivity(intent);
            }
        });
        return convertView;

    }
}
