package com.example.haeunkim.mealtime.viewmodel;


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
import com.example.haeunkim.mealtime.model.Util;
import com.example.haeunkim.mealtime.model.Waiting;

import java.util.concurrent.CopyOnWriteArrayList;

public class WaitingAdapter extends RecyclerView.Adapter<WaitingAdapter.ViewHolder> {

    Context context;
    CopyOnWriteArrayList<Waiting> waitingList;
    int waitingLayout;

    Auth auth;

    public WaitingAdapter(Context context, int waitingLayout) {
        this.context = context;
        this.waitingList = new CopyOnWriteArrayList<>();
        this.auth = new Auth();
        this.waitingLayout = waitingLayout;
    }

    public void add(Waiting w) {
        waitingList.add(w);
        notifyDataSetChanged();
    }

    public void remove(Waiting w) {
        for (Waiting waiting : waitingList) {
            if (waiting.getUid().equals(w.getUid())) {
                waitingList.remove(waiting);
            }
        }

        notifyDataSetChanged();
    }

    public void change(Waiting w) {
        for (Waiting waiting : waitingList) {
            if (waiting.getUid().equals(w.getUid())) {
                waitingList.remove(waiting);
                waitingList.add(w);
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Waiting waiting = waitingList.get(position);
        Drawable drawable = null;

        switch (waiting.getCategory()) {
            case "Cafeteria1":
                drawable = ContextCompat.getDrawable(context, R.drawable.cafeteria_1);
                break;
            case "Cafeteria2":
                drawable = ContextCompat.getDrawable(context, R.drawable.cafeteria_2);
                break;
            case "Cafeteria3":
                drawable = ContextCompat.getDrawable(context, R.drawable.cafeteria_3);
                break;
            case "Western":
                drawable = ContextCompat.getDrawable(context, R.drawable.western);
                break;
            case "Japanese":
                drawable = ContextCompat.getDrawable(context, R.drawable.japanese);
                break;
            case "Korean":
                drawable = ContextCompat.getDrawable(context, R.drawable.korean);
                break;
            case "Chinese":
                drawable = ContextCompat.getDrawable(context, R.drawable.chinese);
                break;
            default:
                drawable = ContextCompat.getDrawable(context, R.drawable.any);
                break;
       }

        holder.image.setBackground(drawable);
        holder.nickName.setText(waiting.getNickname());
        holder.major.setText(waiting.getMajor());
        holder.cardView.setOnClickListener((v) ->
            Util.showMessage(context, waiting.getNickname())
        );
    }

    @Override
    public int getItemCount() {
        return this.waitingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView nickName;
        TextView major;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.item_image);
            nickName = (TextView) view.findViewById(R.id.item_nickname);
            major = (TextView) view.findViewById(R.id.item_major);
            cardView = (CardView) view.findViewById(R.id.cardview);
        }
    }
}