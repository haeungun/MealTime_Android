package com.example.haeunkim.mealtime.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.databinding.ChatBinding;
import com.example.haeunkim.mealtime.model.Auth;
import com.example.haeunkim.mealtime.model.Chat;
import com.example.haeunkim.mealtime.viewmodel.ChatAdapter;
import com.example.haeunkim.mealtime.viewmodel.ChatViewModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class ChatActivity extends AppCompatActivity {

    private ChatBinding binding;
    private ChatViewModel viewModel;

    ChatAdapter chatAdapter;
    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initDataBinding();

        RecyclerView recyclerChat = binding.recycleChat;
        chatAdapter = new ChatAdapter(this, R.layout.chat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerChat.setHasFixedSize(false);
        recyclerChat.setLayoutManager(layoutManager);
        recyclerChat.setAdapter(chatAdapter);

        scroll = binding.scrollChat;
        scroll.post(() ->
                scroll.scrollTo(0, scroll.getBottom()));

        viewModel.onCreate();

        Auth auth = new Auth();
        auth.getReference().child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Chat chat = dataSnapshot.getValue(Chat.class);
                chatAdapter.add(chat);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.chat);
        viewModel = new ChatViewModel(this);
        binding.setViewModel(viewModel);
    }

}
