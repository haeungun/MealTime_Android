package com.example.haeunkim.mealtime.viewmodel;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.model.Auth;
import com.example.haeunkim.mealtime.model.Chat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    Context context;
    List<Chat> chatList;
    Auth auth;
    String uid;
    int chatLayout;

    public ChatAdapter(Context context, int chatLayout) {
        this.context = context;
        this.chatList = new ArrayList<>();
        this.chatLayout = chatLayout;
        this.auth = new Auth();
    }

    public void add(Chat chat) {
        chatList.add(chat);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Chat chat = chatList.get(position);
        holder.name.setText(chat.getName());
        holder.message.setText(chat.getMessage());

        uid = auth.getCurrentUid();
        if (chat.getUid().equals(this.uid)) {
            Drawable drawable = context.getResources().getDrawable(R.drawable.chat_my_name);
            holder.name.setBackground(drawable);
        }
    }


    @Override
    public int getItemCount() {
        return this.chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView message;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.chat_name);
            message = (TextView) view.findViewById(R.id.chat_message);
        }
    }
}
