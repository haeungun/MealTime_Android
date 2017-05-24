package com.example.haeunkim.mealtime.viewmodel;


import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.model.Auth;
import com.example.haeunkim.mealtime.model.Util;

public class SignUpViewModel implements ViewModel {
    private Context context;
    private Auth auth;

    public final ObservableField<String> nickName = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> pwd = new ObservableField<>();
    public String major;


    public SignUpViewModel(@NonNull Context context) {
        this.context = context;
        this.auth = new Auth();
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

    public void onClickSignUp(View v) {
        String nickname = this.nickName.get();
        String email = this.email.get();
        String pwd = this.pwd.get();

        if (nickname == null || email == null || pwd == null ||
                nickname.length() < 1 || email.length() < 1 || pwd.length() < 1) {
            Util.showMessage(context, context.getString(R.string.error_empty));
        } else {
            auth.signUpUser(context, email, pwd, nickname, major);
        }
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            nickName.set(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            email.set(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public TextWatcher pwdWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            pwd.set(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
