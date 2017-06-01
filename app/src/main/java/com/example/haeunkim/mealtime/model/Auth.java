package com.example.haeunkim.mealtime.model;

import android.content.Context;
import android.util.Log;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.view.LoginActivity;
import com.example.haeunkim.mealtime.view.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.StringJoiner;

public class Auth {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    private String name;
    private String major;

    public void signUpUser(final Context context, final String email, String pwd, final String name, final String major) {
        firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener((v) -> {
            if (v.isSuccessful()) {
                saveUserInfo(name, major);
                Util.showMessage(context, "Sign up success");
                Util.goActivity(context, LoginActivity.class);
            } else {
                Util.showMessage(context, context.getString(R.string.error));
            }
        });
    }

    public void signInUser(final Context context, String email, String pwd) {
        firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener((v) -> {
            if (v.isSuccessful()) {
                Util.goActivity(context, MainActivity.class);
            } else {
                Util.showMessage(context, context.getString(R.string.error));
            }
        });
    }

    public void signOutUser() {
        firebaseAuth.signOut();
    }

    public boolean isAuthenticated() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            Log.d("AUTHENTICATED", "onAuthStateChanged:signed_in:" + user.getUid());
            return true;
        } else {
            Log.d("AUTHENTICATED", "onAuthStateChanged:signed_out");
            return false;
        }
    }

    public void saveUserInfo(String name, String major) {
        User user = new User(name, major);
        String uid = firebaseAuth.getCurrentUser().getUid();
        reference.child("users").child(uid).setValue(user);
    }

    public String getCurrentUid() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    public void getCurrentUserName() {
        String uid = firebaseAuth.getCurrentUser().getUid();
        Log.d("UID : ", uid);
        reference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> user = (HashMap<String, String>) dataSnapshot.getValue();
                name = user.get("nickname");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void getCurrentUserMajor() {
        String uid = firebaseAuth.getCurrentUser().getUid();
        reference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> user = (HashMap<String, String>) dataSnapshot.getValue();
                major = user.get("major");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public User getCurrentUser() {
        this.getCurrentUserName();
        this.getCurrentUserMajor();
        Log.d("CURRENT :", name + " " + major);
        User user = new User(name, major);
        return user;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public DatabaseReference getReference() {
        return reference;
    }
}
