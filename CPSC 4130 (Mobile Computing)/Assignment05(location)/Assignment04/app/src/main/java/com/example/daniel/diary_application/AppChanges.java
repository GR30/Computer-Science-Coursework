package com.example.daniel.diary_application;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.RelativeLayout;


public class AppChanges {

    public AppChanges(Context con, RelativeLayout v) {
        SharedPreferences prefered = con.getSharedPreferences("Lock", Context.MODE_PRIVATE);
        String Red, Green ,Blue;
        if (prefered.getString("Red",null) == null &&
                prefered.getString("Green",null) == null &&
                prefered.getString("Blue",null) == null) {
            v.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        else {
            Red = prefered.getString("Red",null);
            Green = prefered.getString("Green",null);
            Blue = prefered.getString("Blue",null);
            if (Integer.parseInt(Red,16) <= 15 &&
                    Integer.parseInt(Green,16) <= 15 &&
                    Integer.parseInt(Blue,16) <= 15) {
                Red = "0" + Red;
                Green = "0" + Green;
                Blue = "0" + Blue;
            }
            v.setBackgroundColor(Color.parseColor("#"+Red+Green+Blue));
        }
    }
}