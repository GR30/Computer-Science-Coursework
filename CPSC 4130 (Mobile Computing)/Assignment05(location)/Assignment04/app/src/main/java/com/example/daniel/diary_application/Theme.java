package com.example.daniel.diary_application;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class Theme extends AppCompatActivity {

    SeekBar seek_bar_red,seek_bar_green,seek_bar_blue;
    SharedPreferences prefrences;
    RelativeLayout realitive_layout;
    String hexR,hexG,hexB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        realitive_layout = (RelativeLayout)findViewById(R.id.theme);
        final TextView rc = (TextView) findViewById(R.id.textR);
        final TextView gc = (TextView) findViewById(R.id.textG);
        final TextView bc = (TextView) findViewById(R.id.textB);
        seek_bar_red = (SeekBar) findViewById(R.id.Rcolor);
        seek_bar_green = (SeekBar) findViewById(R.id.Gcolor);
        seek_bar_green = (SeekBar) findViewById(R.id.Bcolor);
        prefrences = this.getSharedPreferences("Lock", Context.MODE_PRIVATE);
        if (prefrences.getString("Red",null) == null &&
                prefrences.getString("Green",null) == null &&
                prefrences.getString("Blue",null) == null) {
            hexR = "ff";
            hexG = "ff";
            hexB = "ff";
            seek_bar_red.setProgress(Integer.parseInt(hexR,16));
            seek_bar_green.setProgress(Integer.parseInt(hexG,16));
            seek_bar_blue.setProgress(Integer.parseInt(hexB,16));
            realitive_layout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        else {
            hexR = prefrences.getString("Red",null);
            hexG = prefrences.getString("Green",null);
            hexB = prefrences.getString("Blue",null);
            realitive_layout.setBackgroundColor(Color.parseColor("#"+hexR+hexG+hexB));
            seek_bar_blue.setProgress(Integer.parseInt(hexR,16));
            seek_bar_green.setProgress(Integer.parseInt(hexG,16));
            seek_bar_blue.setProgress(Integer.parseInt(hexB,16));
        }
        seek_bar_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i>15) {
                    hexR = Integer.toHexString(i);
                }
                else {
                    hexR = "0"+ Integer.toHexString(i);
                }
                rc.setText("Red : " + hexR);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                prefrences.edit().putString("Red",hexR).commit();
                Log.d("notice","#"+hexR+hexG+hexB);
                realitive_layout.setBackgroundColor(Color.parseColor("#"+hexR+hexG+hexB));
                DiaryList.diary_list.rltve_layout.setBackgroundColor(Color.parseColor("#"+hexR+hexG+hexB));
            }
        });
        seek_bar_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i>15) {
                    hexG = Integer.toHexString(i);
                }
                else {
                    hexG = "0"+ Integer.toHexString(i);
                }
                gc.setText("Green : " + hexG);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                prefrences.edit().putString("Green",hexG).commit();
                Log.d("notice","#"+hexR+hexG+hexB);
                realitive_layout.setBackgroundColor(Color.parseColor("#"+hexR+hexG+hexB));
                DiaryList.diary_list.rltve_layout.setBackgroundColor(Color.parseColor("#"+hexR+hexG+hexB));
            }
        });
        seek_bar_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i>15) {
                    hexB = Integer.toHexString(i);
                }
                else {
                    hexB = "0"+ Integer.toHexString(i);
                }
                bc.setText("Blue : " + hexB);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                prefrences.edit().putString("Blue",hexB).commit();
                Log.d("notice","#"+hexR+hexG+hexB);
                realitive_layout.setBackgroundColor(Color.parseColor("#"+hexR+hexG+hexB));
                DiaryList.diary_list.rltve_layout.setBackgroundColor(Color.parseColor("#"+hexR+hexG+hexB));
            }
        });
    }
}
