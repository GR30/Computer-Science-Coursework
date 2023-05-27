package com.example.gwblu.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import java.util.concurrent.TimeUnit;

public class Timer_Activity extends AppCompatActivity implements View.OnClickListener
{
    private static TextView ctdnText;
    private static int i_hrs, i_mins, i_secs;
    private static TextView o_hrs, o_mins, o_secs;
    private static Button start_Timer, reset_Timer, incr_hrs, decr_hrs, incr_mins, decr_mins, incr_secs, decr_secs;
    private static CountDownTimer count_Down_Timer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity_design); //ability to view timer screen
        ctdnText = findViewById(R.id.ctndwnText);
        i_hrs = 0;
        i_mins = 0;
        i_secs = 0;
        o_hrs = findViewById(R.id.enterHours);
        o_mins = findViewById(R.id.enterMinutes);
        o_secs = findViewById(R.id.enterSeconds);
        start_Timer = findViewById(R.id.startTimer);
        reset_Timer = findViewById(R.id.resetTimer);
        incr_hrs = findViewById(R.id.button);
        decr_hrs = findViewById(R.id.button2);
        incr_mins = findViewById(R.id.button3);
        decr_mins = findViewById(R.id.button4);
        incr_secs = findViewById(R.id.button5);
        decr_secs = findViewById(R.id.button6);
        setListeners();
    }
    //Set Listeners over button
    private void setListeners()
    {
        start_Timer.setOnClickListener(this);
        reset_Timer.setOnClickListener(this);
        incr_secs.setOnClickListener(this);
        decr_secs.setOnClickListener(this);
        incr_mins.setOnClickListener(this);
        decr_mins.setOnClickListener(this);
        incr_hrs.setOnClickListener(this);
        decr_hrs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.startTimer:
                //If CountDownTimer is null then start timer
                if (count_Down_Timer == null)
                {
                    String getHours = o_hrs.getText().toString();
                    String getMinutes = o_mins.getText().toString();
                    String getSeconds = o_secs.getText().toString();
                    if ((!getMinutes.equals("") && getMinutes.length() > 0)&&(!getMinutes.equals("") && getMinutes.length() > 0)&&(!getMinutes.equals("") && getMinutes.length() > 0))
                    {
                        int numOfHours = Integer.parseInt(getHours) * 60 * 60 * 1000;
                        int numOfMinutes = Integer.parseInt(getMinutes) * 60 * 1000;
                        int numOfSeconds = Integer.parseInt(getSeconds) * 1000;
                        int numOfMillis = numOfHours + numOfMinutes + numOfSeconds;
                        startTimer(numOfMillis);
                        start_Timer.setText("Stop");
                    }
                    else
                        Toast.makeText(Timer_Activity.this, "The time is entered here.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    stopCountdown();
                    start_Timer.setText("Start");
                }
                break;

            case R.id.resetTimer:
                stopCountdown();
                start_Timer.setText("Start");
                ctdnText.setText("Timer");
                i_hrs = 0;
                o_hrs.setText(i_hrs + "");
                i_mins = 0;
                o_mins.setText(i_mins + "");
                i_secs = 0;
                o_secs.setText(i_secs + "");
                break;

            case R.id.button: i_hrs++; o_hrs.setText(i_hrs + ""); break;
            case R.id.button2: i_hrs--; o_hrs.setText(i_hrs + ""); break;
            case R.id.button3: i_mins++; o_mins.setText(i_mins + ""); break;
            case R.id.button4: i_mins--; o_mins.setText(i_mins + ""); break;
            case R.id.button5: i_secs++; o_secs.setText(i_secs + ""); break;
            case R.id.button6: i_secs--; o_secs.setText(i_secs + ""); break;
        }
    }
    private void stopCountdown()
    {
        if (count_Down_Timer != null)
        {
            count_Down_Timer.cancel();
            count_Down_Timer = null;
        }
    }
    private void startTimer(int noOfMinutes)
    {
        count_Down_Timer = new CountDownTimer(noOfMinutes, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                long millis = millisUntilFinished;
                String hours = String.format("%2d", TimeUnit.MILLISECONDS.toHours(millis));
                String minutes = String.format("%2d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
                String seconds = String.format("%2d", TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                o_secs.setText(seconds);
                o_mins.setText(minutes);
                o_hrs.setText(hours);

            }
            public void onFinish()
            {
                ctdnText.setText("Done");
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                long[] pattern = {0, 100, 1000, 300, 200, 100, 500, 200, 100};
                v.vibrate(pattern, -1);
                count_Down_Timer = null;
                start_Timer.setText("Start Timer");
            }
        }.start();
    }
}

