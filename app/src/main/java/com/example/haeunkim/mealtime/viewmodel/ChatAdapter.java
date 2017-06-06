package com.example.haeunkim.mealtime.viewmodel;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

// Adapter for chat card view
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    Context context;
    List<Chat> chatList;
    Auth auth;
    int chatLayout;

    String uid;

    public ChatAdapter(Context context, int chatLayout) {
        this.context = context;
        this.chatList = new ArrayList<>();
        this.chatLayout = chatLayout;
        this.auth = new Auth();
    }

    public void add(Chat chat) {
        chatList.add(chat);
        notifyDataSetChanged();
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
        holder.date.setText(chat.getDate());
        holder.message.setText(chat.getMessage());

        uid = auth.getCurrentUid();
        if (chat.getUid().equals(this.uid)) {
            // If chat is 'my' message,
            // (The uid of the sender of the message is the same as the uid of the currently logged in user)
            // set the background color to transparent_10 and the name color to purple.
            Log.d("CHAT_UID", chat.getUid() + " / CURRENT_UID : " + uid);
            holder.cardView.setCardBackgroundColor(context.getColor(R.color.transparent_10));
            holder.name.setTextColor(context.getColor(R.color.purple));
        }
    }

    @Override
    public int getItemCount() {
        return this.chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView date;
        TextView message;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.chat_name);
            date = (TextView) view.findViewById(R.id.chat_date);
            message = (TextView) view.findViewById(R.id.chat_message);
            cardView = (CardView) view.findViewById(R.id.card_chat);
        }
    }
}
