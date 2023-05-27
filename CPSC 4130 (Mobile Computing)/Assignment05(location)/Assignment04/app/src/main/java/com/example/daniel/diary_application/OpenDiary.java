package com.example.daniel.diary_application;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class OpenDiary extends AppCompatActivity {

    int year,month,dayOfMonth,dayOfWeek,hour,minute,second;
    String day_Name, month_Name;
    String[] string_Days, string_Months;
    Calendar google_Calendar;
    DataHelper data_base_Helper;
    protected Cursor cursor;
    RelativeLayout relative_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_diary);
        data_base_Helper = new DataHelper(this);
        google_Calendar = (GregorianCalendar) GregorianCalendar.getInstance(TimeZone.getDefault());
        final EditText dt = (EditText) findViewById(R.id.DateTime);
        final EditText title = (EditText) findViewById(R.id.title);
        final EditText content = (EditText) findViewById(R.id.content);
        Button showLocationEntered = (Button) findViewById(R.id.Location_button);
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        relative_layout = (RelativeLayout) findViewById(R.id.rl);
        new AppChanges(this,relative_layout);

        showLocationEntered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(OpenDiary.this,MapsActivity.class);
                startActivity(k);
            }
        });

        SQLiteDatabase db = data_base_Helper.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM diary WHERE dt = DATETIME('" +
//                getIntent().getStringExtra("diary") + "')" , null);
        cursor = db.rawQuery("SELECT * FROM diary WHERE title = '" +
                getIntent().getStringExtra("diary") + "' AND content ='" +
                        getIntent().getStringExtra("con") + "' AND dt = DATETIME('" +
                getIntent().getStringExtra("date") + "')", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            String saved = cursor.getString(0).toString();
            try {
                google_Calendar.setTime(dateFormatter.parse(saved));
                CalendarNow();
                dt.setText(day_Name + ", " + dayOfMonth + " " + month_Name + " " + year);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            title.setText(cursor.getString(1).toString());
            content.setText(cursor.getString(2).toString());
        }
        dt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                DatePickerDialog datepicker = new DatePickerDialog(OpenDiary.this, new DatePickerDialog.OnDateSetListener() {
                    public  void onDateSet(DatePicker view, int SetYear, int SetMonth, int SetDay ) {
                        google_Calendar.set(SetYear,SetMonth,SetDay,hour,minute,second);
                        CalendarNow();
                        dt.setText(day_Name + ", " + dayOfMonth + " " + month_Name + " " + year);
                    }
                }, year, month-1, dayOfMonth);

                datepicker.show();
            }
        });
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFormatter.setCalendar(google_Calendar);
                SQLiteDatabase db = data_base_Helper.getWritableDatabase();
                db.execSQL("UPDATE diary SET dt = '" + dateFormatter.format(google_Calendar.getTime()) +
                        "', title = '" + title.getText().toString() +
                        "', content = '"+ content.getText().toString() +
                        "' WHERE dt = DATETIME('"+ getIntent().getStringExtra("date") + "')");
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                DiaryList.diary_list.RefreshList();
                finish();
            }
        });
        Button delButton = (Button) findViewById(R.id.delButton);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFormatter.setCalendar(google_Calendar);
                SQLiteDatabase db = data_base_Helper.getWritableDatabase();
                db.execSQL("DELETE FROM diary WHERE dt = DATETIME('"+ getIntent().getStringExtra("date") + "')");
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                DiaryList.diary_list.RefreshList();
                finish();
            }
        });
    }

    public void CalendarNow() {
        year = google_Calendar.get(Calendar.YEAR);
        month = google_Calendar.get(Calendar.MONTH)+1;
        string_Months = new String[] { "December", "January", "February", "March", "April", "Mei", "June",
                "July", "August", "September", "October", "November", "December" };
        month_Name = string_Months[month];
        dayOfMonth = google_Calendar.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = google_Calendar.get(Calendar.DAY_OF_WEEK);
        string_Days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
                "Friday", "Saturday", "Sunday" };
        day_Name = string_Days[dayOfWeek];
        hour = google_Calendar.get(Calendar.HOUR_OF_DAY);
        minute = google_Calendar.get(Calendar.MINUTE);
        second = google_Calendar.get(Calendar.SECOND);
    }
}
