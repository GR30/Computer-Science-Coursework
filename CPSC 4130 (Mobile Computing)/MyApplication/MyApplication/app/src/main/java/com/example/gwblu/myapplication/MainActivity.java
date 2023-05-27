package com.example.gwblu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button calculator1;
    private Button timer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator1 = findViewById(R.id.Calculator);
        calculator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalc();

            }
        });


        timer1 = findViewById(R.id.Timer_Button);
        timer1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view1)
            {
                openTmr();

            }
        });
    }


    public void openCalc(){
        Intent i = new Intent(this, Calculator_Activity.class);
        startActivity(i);
    }

    public void openTmr()
    {
        Intent i = new Intent(this, Timer_Activity.class);
        startActivity(i);
    }
}