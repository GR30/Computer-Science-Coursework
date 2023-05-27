package com.example.gwblu.calc_timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_menu extends AppCompatActivity {

    private Button calculator1;
    private Button timer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        calculator1 = findViewById(R.id.calculator);
        calculator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalc();

            }
        });


        timer1 = findViewById(R.id.timer);
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
        Intent i = new Intent(this, calculator_activity.class);
        startActivity(i);
    }

    public void openTmr()
    {
        Intent j = new Intent(this, Timer_Activity.class);
        startActivity(j);
    }
}

