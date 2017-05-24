package com.example.haeunkim.mealtime.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.databinding.PopUpBinding;
import com.example.haeunkim.mealtime.model.Auth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PopUpActivity extends AppCompatActivity {

    private Spinner spinCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        List<String> categoryList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categoryList);

        Auth auth = new Auth();
        auth.getReference().child("categories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> categories = (ArrayList<String>) dataSnapshot.getValue();
                for (String category : categories) {
                    categoryList.add(category);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        PopUpBinding popUpBinding = DataBindingUtil.setContentView(this, R.layout.pop_up);
        spinCategory = popUpBinding.spinCategory;
        spinCategory.setAdapter(adapter);
        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SELECTED : " , parent.getItemAtPosition(position).toString());
                // viewModel.setMajor(parent.getItemAtPosition(position).toString());
                // ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }


}
