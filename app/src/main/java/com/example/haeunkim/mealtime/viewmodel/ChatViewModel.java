package com.example.haeunkim.mealtime.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.haeunkim.mealtime.model.Auth;
import com.example.haeunkim.mealtime.model.Chat;
import com.example.haeunkim.mealtime.model.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ChatViewModel implements ViewModel {

    private Auth auth;
    private Context context;

    public String name;
    public final ObservableField<String> content = new ObservableField<>();

    public ChatViewModel(@NonNull Context context) {
        this.context = context;
        this.auth = new Auth();
    }

    @Override
    public void onCreate() {
        this.setUserName();
    }

    @Override
    public void onPause() {}

    @Override
    public void onResume() {}

    @Override
    public void onDestroy() {}

    public void onClickSend(View v) {
        if (content.get() == null || content.get().length() < 1) {
            Util.showMessage(context, "Input your message");
        } else {
            this.sendMessage();
        }
    }

    private void setUserName() {
        String uid = auth.getCurrentUid();
        auth.getReference().child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> user = (HashMap<String, String>) dataSnapshot.getValue();
                name = user.get("nickname");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void sendMessage() {
        String uid = auth.getCurrentUid();
        Chat chat = new Chat(uid, name, content.get());
        auth.getReference().child("chat").push().setValue(chat);
        content.set("");
    }

    public TextWatcher msgWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            content.set(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };
}
