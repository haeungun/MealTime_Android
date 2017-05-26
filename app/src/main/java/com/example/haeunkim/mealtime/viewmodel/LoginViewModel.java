package com.example.haeunkim.mealtime.viewmodel;


import android.content.Context;
import android.databinding.ObservableField;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.model.Auth;
import com.example.haeunkim.mealtime.model.Util;
import com.example.haeunkim.mealtime.view.MainActivity;
import com.example.haeunkim.mealtime.view.SignUpActivity;

public class LoginViewModel implements ViewModel {
    private Context context;

    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> pwd = new ObservableField<>();

    public LoginViewModel(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        Log.d("WIFI_NAME", getWifiName() + "");
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

    public String getWifiName() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID();
        return ssid;
    }

    public void onClickLogin(View v) {
        //Log.d("WIFI_NAME", getWifiName() + " is connected.");
        String email = this.email.get();
        String pwd = this.pwd.get();

        if (email == null || pwd == null || email.length() < 1 || pwd.length() < 1) {
            Util.showMessage(context, context.getString(R.string.error_empty));
        } else {
            Auth auth = new Auth();
            auth.signInUser(context, email, pwd);
        }
    }

    public void onClickSignUp(View v) {
        Util.goActivity(context, SignUpActivity.class);
    }

    public TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            email.set(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {}
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
