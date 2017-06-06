package com.example.haeunkim.mealtime.model;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.haeunkim.mealtime.viewmodel.ChatAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ChatService {

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private ChatAdapter chatAdapter;
    private RecyclerView recyclerChat;

    public ChatService(Context context, int layout, RecyclerView view) {
        this.database = FirebaseDatabase.getInstance();
        this.reference = this.database.getReference();
        this.chatAdapter = new ChatAdapter(context, layout);

        this.recyclerChat = view;
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerChat.setHasFixedSize(false);
        recyclerChat.setLayoutManager(layoutManager);
        recyclerChat.setAdapter(chatAdapter);
    }

    public void run() {

        this.reference.child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Chat chat = dataSnapshot.getValue(Chat.class);
                chatAdapter.add(chat);
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
