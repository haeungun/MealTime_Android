package com.example.haeunkim.mealtime.view;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.haeunkim.mealtime.R;

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);

        ImageView profile = (ImageView) findViewById(R.id.profile_img);
        this.imageToCicular(profile);
    }

    private void imageToCicular(ImageView img) {
        img.setBackground(new ShapeDrawable(new OvalShape()));
        img.setClipToOutline(true);
    }
}
