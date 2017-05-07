package com.example.haeunkim.mealtime.viewmodel;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

public class LoginViewModel implements ViewModel {
    private Context context;

    public LoginViewModel(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onClickLogin(View v) {}

    public void onClickSignUp(View v) {}
}
