package com.example.bookmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanager.R;
import com.example.bookmanager.dao.BookDAO;
import com.example.bookmanager.model.Book;

import java.util.List;

public class BookRecAdapter extends RecyclerView.Adapter<BookRecAdapter.RecyclerViewHolder> {
    Context context;
    List<Book> bookList;
    BookDAO bookDAO;
    LayoutInflater inflater;

    public BookRecAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
        this.bookDAO = new BookDAO(context);
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

    @Override
    public BookRecAdapter.RecyclerViewHolder onCreateViewHolder( ViewGroup parent, final int i) {
        View viewXML = inflater.inflate(R.layout.item_list_book, parent, false);
        BookRecAdapter.RecyclerViewHolder viewHolder = new BookRecAdapter.RecyclerViewHolder(viewXML);
        viewHolder.ivIcon = (ImageView) viewXML.findViewById(R.id.imgBook);
        viewHolder.tvCateID = (TextView) viewXML.findViewById(R.id.tvBookID);
        viewHolder.tvCateName = (TextView) viewXML.findViewById(R.id.tvBookName);
        viewHolder.ivDelete = (ImageView) viewXML.findViewById(R.id.imgDelete);
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookID = bookList.get(i).getBookID();
                bookDAO.deleteBook(bookID);
                Book book = bookList.get(i);
                bookList.remove(book);
                notifyDataSetChanged();
            }
        });
        return null;
    }

    @Override
    public void onBindViewHolder( BookRecAdapter.RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
