package com.example.haeunkim.mealtime.model;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Util {

    public static void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void goActivity(Context context, Class aClass) {
        Intent intent = new Intent(context, aClass);
        context.startActivity(intent);
    }

}
