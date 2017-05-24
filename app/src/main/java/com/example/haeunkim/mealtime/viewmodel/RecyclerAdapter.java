package com.example.haeunkim.mealtime.viewmodel;


import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.model.Auth;
import com.example.haeunkim.mealtime.model.Item;
import com.example.haeunkim.mealtime.model.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Item> itemList;
    private int itemLayout;

    private Auth auth;

    public RecyclerAdapter(Context context, int itemLayout) {
        this.context = context;
        this.itemList = new ArrayList<>();
        this.auth = new Auth();

        Item[] item = new Item[5];
        item[0] = new Item(R.drawable.dinner1, "내이름은", "인문대학 국어국문학과");
        item[1] = new Item(R.drawable.dinner1, "김헤헿", "사회과학대학 심리학과");
        item[2] = new Item(R.drawable.dinner1, "탐정이죠", "사회과학대학 심리학과");
        item[3] = new Item(R.drawable.dinner1, "진실은", "사회과학대학 심리학과");
        item[4] = new Item(R.drawable.dinner1, "언제나하나", "사회과학대학 심리학과");
        for (int i = 0; i < item.length; i++) {
            itemList.add(item[i]);
        }
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview, null, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = itemList.get(position);
        Drawable drawable = ContextCompat.getDrawable(context, item.getImgae());
        holder.image.setBackground(drawable);
        holder.nickName.setText(item.getNickName());
        holder.major.setText(item.getMajor());
        holder.cardView.setOnClickListener(view ->
            Util.showMessage(context, item.getNickName())
        );
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nickName;
        TextView major;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_image);
            nickName = (TextView) itemView.findViewById(R.id.item_nickname);
            major = (TextView) itemView.findViewById(R.id.item_major);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }

    private void setItems() {
        auth.getReference().child("waiting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Item> items = (ArrayList<Item>) dataSnapshot.getValue();
                for (Item item : items) {
                    itemList.add(item);
                }
                Util.showMessage(context, items.size() + " !");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Util.showMessage(context, "ERROR");
            }
        });
    }
}