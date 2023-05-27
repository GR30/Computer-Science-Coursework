package com.example.gwblu.calc_timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Timer_Activity extends AppCompatActivity implements View.OnClickListener{

    private static TextView ctndwnText, minutes, hours, seconds;
    private static Button startTimer, resetTimer;
    private static CountDownTimer countDownTimer;
    private static int hrs, mins, secs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer); //ability to view timer screen

        ctndwnText = (TextView) findViewById(R.id.ctndwnText);
        hours = (TextView) findViewById(R.id.enterHours);
        minutes = (TextView) findViewById(R.id.enterMinutes);
        seconds = (TextView) findViewById(R.id.enterSeconds);
        startTimer = (Button) findViewById(R.id.startTimer);
        resetTimer = (Button) findViewById(R.id.resetTimer);

        setListeners();
    }


    //Set Listeners over button
    private void setListeners() {
        startTimer.setOnClickListener(this);
        resetTimer.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTimer:

                //If CountDownTimer is null then start timer
                if (countDownTimer == null) {
                    String getMinutes = minutes.getText().toString();
                    if (!getMinutes.equals("") && getMinutes.length() > 0) {
                        int noOfMinutes = Integer.parseInt(getMinutes) * 60 * 1000;

                        startTimer(noOfMinutes);
                        startTimer.setText(getString(R.string.stop_timer));
                    } else
                        Toast.makeText(Timer_Activity.this, "Please enter the time.", Toast.LENGTH_SHORT).show();
                } else {
                    stopCountdown();
                    startTimer.setText(getString(R.string.start_timer));
                }
                break;

            case R.id.resetTimer:
                stopCountdown();
                startTimer.setText(getString(R.string.start_timer));
                ctndwnText.setText(getString(R.string.timer));
                break;

            case R.id.button:
                hrs++;
                hours.setText(hrs);
                break;

            case R.id.button2:
                hrs--;
                hours.setText(hrs);
                break;

            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:

        }
    }


    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }


    private void startTimer(int noOfMinutes) {

        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {

            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                ctndwnText.setText(hms);
            }

            public void onFinish() {
                ctndwnText.setText("Timer Done.");
                countDownTimer = null;
                startTimer.setText(getString(R.string.start_timer));
            }
        }.start();
    }
}