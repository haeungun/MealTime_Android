package com.example.haeunkim.mealtime.viewmodel;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.haeunkim.mealtime.model.Util;
import com.example.haeunkim.mealtime.view.SignUpActivity;

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

    public void onClickLogin(View v) {
       // Util.goActivity(context, MainActivity.class);
    }

    public void onClickSignUp(View v) {
        Util.goActivity(context, SignUpActivity.class);
    }
}
