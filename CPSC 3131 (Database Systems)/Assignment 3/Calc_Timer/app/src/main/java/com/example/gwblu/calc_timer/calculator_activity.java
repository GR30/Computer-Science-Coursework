package com.example.gwblu.calc_timer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class calculator_activity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAddition, buttonSubtract, buttonDivide,
            buttonMultlply, buttonDecimal, buttonC, buttonEqual, buttonCE, buttonDeleate;

    EditText textWindow;

    Float Value1, Value2;

    boolean Addition, Subtraction, Multiplication, Division;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        button0 = (Button) findViewById(R.id.Zero_button);
        button1 = (Button) findViewById(R.id.One_button);
        button2 = (Button) findViewById(R.id.Two_button);
        button3 = (Button) findViewById(R.id.Three_button);
        button4 = (Button) findViewById(R.id.Four_button);
        button5 = (Button) findViewById(R.id.Five_button);
        button6 = (Button) findViewById(R.id.Six_button);
        button7 = (Button) findViewById(R.id.Seven_button);
        button8 = (Button) findViewById(R.id.Eight_button);
        button9 = (Button) findViewById(R.id.Nine_button);
        buttonAddition = (Button) findViewById(R.id.Plus_button);
        buttonSubtract = (Button) findViewById(R.id.Minus_button);
        buttonMultlply = (Button) findViewById(R.id.Mutlply_button);
        buttonDivide = (Button) findViewById(R.id.Divide_button);
        buttonEqual = (Button) findViewById(R.id.Equals_button);
        buttonC = (Button) findViewById(R.id.C_button);
        buttonCE = (Button) findViewById(R.id.CE_button);
        buttonDeleate = (Button) findViewById(R.id.Deleate_button);
        buttonDecimal = (Button) findViewById(R.id.Decimal_button);

        textWindow = (EditText) findViewById(R.id.Text_Display);

        button0.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "0");

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "1");

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + "9");
            }
        });

        buttonDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText(textWindow.getText() + ".");
            }
        });

        buttonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textWindow == null)
                {
                    textWindow.setText("");
                } else
                {
                    Value1 = Float.parseFloat(textWindow.getText() + "");
                    Addition = true;
                    textWindow.setText(null);
                }
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Value1 = Float.parseFloat(textWindow.getText() + "");
                Subtraction = true;
                textWindow.setText(null);
            }
        });

        buttonMultlply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Value1 = Float.parseFloat(textWindow.getText() + "");
                Multiplication = true;
                textWindow.setText(null);
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Value1 = Float.parseFloat(textWindow.getText() + "");
                Division = true;
                textWindow.setText(null);
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Value2 = Float.parseFloat(textWindow.getText() + "");

                if (Addition == true) {
                    textWindow.setText(Value1 + Value2 + "");
                    Addition = false;
                }

                if (Subtraction == true) {
                    textWindow.setText(Value1 - Value2 + "");
                    Subtraction = false;
                }

                if (Multiplication == true) {
                    textWindow.setText(Value1 * Value2 + "");
                    Multiplication = false;
                }

                if (Division == true)
                {
                    if(Value2 != 0)
                    {
                        textWindow.setText(Value1 / Value2 + "");
                        Division = false;
                    }
                    else
                    {
                        textWindow.setText("Can Not Divide by 0 ");
                    }

                }
            }


        });


        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText("");
            }
        });

        buttonCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWindow.setText("");
                Addition = false;
                Subtraction = false;
                Division = false;
                Multiplication = false;
                Value1 = null;
                Value2 = null;
            }
        });

        buttonDeleate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String str = textWindow.getText().toString();


                if(str.length() >= 1)
                {
                    str = str.substring(0, str.length() -1);
                    textWindow.setText(str);
                }
                else if(str.length() <= 0)
                {
                    textWindow.setText("0");
                }



            }
        });
    }

}

