package com.example.daniel.diary_application;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Password extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper Data_base_center;
    RelativeLayout relative_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        Data_base_center = new DataHelper(this);
        final Intent intent = new Intent(this, LockerSetting.class);
        final EditText email = (EditText)findViewById(R.id.email);
        final EditText pass = (EditText)findViewById(R.id.password);
        final EditText user = (EditText)findViewById(R.id.username);
        relative_Layout = (RelativeLayout) findViewById(R.id.rl);
        new AppChanges(this,relative_Layout);
        Button create = (Button)findViewById(R.id.create);
        SQLiteDatabase db = Data_base_center.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM account", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            startActivity(intent);
            finish();
        }
        create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                SQLiteDatabase db = Data_base_center.getWritableDatabase();
                db.execSQL("INSERT INTO account(username,password,email) VALUES('" +
                        email.getText().toString() + "','" +
                        user.getText().toString() + "','" +
                        pass.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });
    }
}
