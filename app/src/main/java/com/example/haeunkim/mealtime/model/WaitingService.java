package com.example.haeunkim.mealtime.model;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.haeunkim.mealtime.viewmodel.WaitingAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WaitingService {

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private WaitingAdapter waitingAdapter;
    private RecyclerView recyclerWaiting;
    private TextView txtCount;

    public WaitingService(Context context, int layout, RecyclerView view, TextView txt) {
        this.database = FirebaseDatabase.getInstance();
        this.reference = this.database.getReference();

        this.waitingAdapter = new WaitingAdapter(context, layout);
        this.recyclerWaiting = view;
        this.txtCount = txt;

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerWaiting.setHasFixedSize(true);
        recyclerWaiting.setLayoutManager(layoutManager);
        recyclerWaiting.setAdapter(waitingAdapter);
    }

    public void run() {
        this.waitingList();
        this.count();
    }

    private void waitingList() {
        this.reference.child("waiting").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Waiting w = dataSnapshot.getValue(Waiting.class);
                waitingAdapter.add(w);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Waiting w = dataSnapshot.getValue(Waiting.class);
                waitingAdapter.change(w);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Waiting w = dataSnapshot.getValue(Waiting.class);
                waitingAdapter.remove(w);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void count() {
        this.reference.child("waiting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Setting each time the size of the Waiting node changes
                int size = (int) dataSnapshot.getChildrenCount();
                txtCount.setText(String.valueOf(size));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
