package com.example.haeunkim.mealtime.model;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.view.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Auth {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public void signUpUser(final Context context, final String email, String pwd, final String name, final String major) {
        firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    saveUserInfo(name, major);
                    // Util.showMessage(context, context.getString(R.string.succeess));
                    Util.goActivity(context, LoginActivity.class);
                } else {
                    Util.showMessage(context, context.getString(R.string.error));
                }
            }
        });
    }

    public void signInUser(final Context context, String email, String pwd) {
        firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Util.goActivity(context, MainActivity.class);
                } else {
                    Util.showMessage(context, context.getString(R.string.error));
                }
            }
        });
    }

    public void signOutUser() {
        firebaseAuth.signOut();
    }

    public void saveUserInfo(String name, String major) {
        User user = new User(name, major);
        String uid = firebaseAuth.getCurrentUser().getUid();
        reference.child("users").child(uid).setValue(user);
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
