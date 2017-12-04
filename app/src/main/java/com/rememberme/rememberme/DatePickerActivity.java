package com.rememberme.rememberme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DatePickerActivity extends AppCompatActivity {
    String year;
    String month;
    String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

    }
}
