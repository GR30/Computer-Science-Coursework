package com.example.daniel.diary_application;

import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.TimeZone;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class WriteDiary extends AppCompatActivity {

    int year,month,dayOfMonth,dayOfWeek,hour,minute,second;
    String day, month_Nme;
    String[] string_Days, string_Months;
    Calendar Refrence_Calendar;
    DataHelper dbHelper;
    String dtText;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);
        rl = (RelativeLayout) findViewById(R.id.rl);
        new AppChanges(this,rl);
        dbHelper = new DataHelper(this);
        final Date time = GregorianCalendar.getInstance().getTime();
        Refrence_Calendar = (GregorianCalendar) GregorianCalendar.getInstance(TimeZone.getDefault());
	    CalendarNow();
	    final EditText dt = (EditText) findViewById(R.id.DateTime);
        dt.setText(day + ", " + dayOfMonth + " " + month_Nme + " " + year);
        dt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick (View v) {
                DatePickerDialog datepicker = new DatePickerDialog(WriteDiary.this, new DatePickerDialog.OnDateSetListener() {
                    public  void onDateSet(DatePicker view, int SetYear, int SetMonth, int SetDay ) {
                        Refrence_Calendar.set(SetYear,SetMonth,SetDay,hour,minute,second);
                        CalendarNow();
                        dt.setText(day + ", " + dayOfMonth + " " + month_Nme + " " + year);
                    }
                }, year, month-1, dayOfMonth);

                datepicker.show();
            }
        });
        final EditText title = (EditText) findViewById(R.id.title);
        final EditText content = (EditText) findViewById(R.id.content);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateFormatter.setCalendar(Refrence_Calendar);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO diary(dt,title,content) VALUES('" +
                        dateFormatter.format(Refrence_Calendar.getTime()) + "','" +
                        title.getText().toString() + "','" +
                        content.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Confirm", Toast.LENGTH_LONG).show();
                DiaryList.diary_list.RefreshList();
                DiaryList.diary_list.RefreshCycle();
                finish();
            }
        });

    }
	public void CalendarNow() {
        year = Refrence_Calendar.get(Calendar.YEAR);
		month = Refrence_Calendar.get(Calendar.MONTH)+1;
        string_Months = new String[] { "December", "January", "February", "March", "April", "Mei", "June",
                "July", "August", "September", "October", "November", "December" };
        month_Nme = string_Months[month];
		dayOfMonth = Refrence_Calendar.get(Calendar.DAY_OF_MONTH);
		dayOfWeek = Refrence_Calendar.get(Calendar.DAY_OF_WEEK);
		string_Days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
        "Friday", "Saturday", "Sunday" };
		day = string_Days[dayOfWeek];
		hour = Refrence_Calendar.get(Calendar.HOUR_OF_DAY);
		minute = Refrence_Calendar.get(Calendar.MINUTE);
		second = Refrence_Calendar.get(Calendar.SECOND);
	}
}
