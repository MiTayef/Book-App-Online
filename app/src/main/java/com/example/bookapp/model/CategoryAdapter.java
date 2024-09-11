package com.example.bookapp.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.bookapp.BookItemActivity;
import com.example.bookapp.R;
import com.example.bookapp.ViewPDF;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    List<CategoryItem> itemList;
    Context context;

    public CategoryAdapter(List<CategoryItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View MyView = layoutInflater.inflate(R.layout.category_item, viewGroup, false);

        ImageView cat_img = MyView.findViewById(R.id.cat_img);
        TextView cat_title = MyView.findViewById(R.id.cat_title);
        TextView cat_subtitle = MyView.findViewById(R.id.cat_subTitle);
        CardView catClick = MyView.findViewById(R.id.catClick);

        CategoryItem categoryItem = itemList.get(position);


        Glide.with(context).load(categoryItem.getCat_img()).into(cat_img);
        cat_title.setText(categoryItem.getCat_title());
        cat_subtitle.setText(categoryItem.getCat_subtitle());

        catClick.setOnClickListener(view1 -> {
            Intent intent = new Intent(view1.getContext(), BookItemActivity.class);
            intent.putExtra("ServerUrl",categoryItem.getServerUrl());
            intent.putExtra("title",categoryItem.getCat_title());
            view1.getContext().startActivity(intent);
        });

        return MyView;
    }
}//CategoryAdapter Close Here
