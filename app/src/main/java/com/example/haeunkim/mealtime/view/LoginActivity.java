package com.example.haeunkim.mealtime.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.databinding.LoginBinding;
import com.example.haeunkim.mealtime.viewmodel.LoginViewModel;

public class LoginActivity extends Activity {
    private static String TAG = "LOGIN_ACTIVITY";

    private LoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initDataBinding();
        viewModel.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // overridePendingTransition(0,0);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.login);
        viewModel = new LoginViewModel(this);
        binding.setViewModel(viewModel);
    }

}
