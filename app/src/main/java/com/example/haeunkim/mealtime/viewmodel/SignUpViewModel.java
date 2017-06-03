package com.example.haeunkim.mealtime.viewmodel;


import android.content.Context;
import android.databinding.ObservableField;
import android.net.NetworkInfo;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void onCreate() {}

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
        /*
         *   This is a community app for Chungnam NAtional University students.
         *   Therefore, only Chungnam National University students can join the membership.
         *
         *
         *   if (!this.getWifiName(context).equals("CNU wifi")) {
         *       Util.showMessage(context, context.getString(R.string.error_wifi));
         *       return;
         *   }
        **/

        String nickname = this.nickName.get();
        String email = this.email.get();
        String pwd = this.pwd.get();

        if (isVerified(nickname, email, pwd)) {
            auth.signUpUser(context, email, pwd, nickname, major);
        }

    }

    public String getWifiName(Context context) {
        String ssid = "";

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()) == NetworkInfo.DetailedState.CONNECTED) {
            ssid = wifiInfo.getSSID();
        }

        return ssid;
    }

    private boolean isVerified(String name, String email, String pwd) {
        if (name == null || name.length() < 1 || email == null || email.length() < 1
                || pwd == null || pwd.length() < 1) {
            Util.showMessage(context, context.getString(R.string.error_empty));
            return false;
        }

        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{1,7}$");
        Matcher nameMatcher = namePattern.matcher(name);

        if (!nameMatcher.matches()) {
            Util.showMessage(context, context.getString(R.string.error_nickname));
            return false;
        }

        Pattern emailPattern = Pattern.compile("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(email);

        if (!emailMatcher.matches()) {
            Util.showMessage(context, context.getString(R.string.error_email));
            return false;
        }

        Pattern pwdPattern = Pattern.compile(".{6,}$");
        Matcher pwdMatcher = pwdPattern.matcher(pwd);

        if (!pwdMatcher.matches()) {
            Util.showMessage(context, context.getString(R.string.error_password));
            return false;
        }

        return true;
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
