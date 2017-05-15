package com.example.haeunkim.mealtime.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.databinding.MainBinding;
import com.example.haeunkim.mealtime.viewmodel.MainViewModel;
import com.example.haeunkim.mealtime.viewmodel.RecyclerAdapter;

public class MainActivity extends Activity {
    private MainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initDataBinding();

        RecyclerView recyclerView = binding.recycleview;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RecyclerAdapter(this, R.layout.main));

    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.main);
        viewModel = new MainViewModel(this);
        binding.setViewModel(viewModel);
    }
}
