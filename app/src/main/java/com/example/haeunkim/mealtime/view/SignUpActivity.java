package com.example.haeunkim.mealtime.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.databinding.SignUpBinding;
import com.example.haeunkim.mealtime.model.Auth;
import com.example.haeunkim.mealtime.viewmodel.SignUpViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends Activity {

    private SignUpBinding binding;
    private SignUpViewModel viewModel;

    Spinner spinMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initDataBinding();

        Auth auth = new Auth();
        List<String> majorList = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, majorList);

        auth.getReference().child("majors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> majors = (ArrayList<String>) dataSnapshot.getValue();
                Log.d("MAJORS_SIZE", majors.size() + "");
                for (String major : majors) {
                    majorList.add(major);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        spinMajor = binding.spin;
        spinMajor.setAdapter(adapter);

        spinMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SELECTED : " , parent.getItemAtPosition(position).toString());
                viewModel.setMajor(parent.getItemAtPosition(position).toString());
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.sign_up);
        viewModel = new SignUpViewModel(this);
        binding.setViewModel(viewModel);
    }

}
