package com.rememberme.rememberme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AddTitleActivity extends AppCompatActivity {
//여행 제목과 달력 입력하는 뷰
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_title);

    AddTitleFragment addTitleFragment = new AddTitleFragment();

    getSupportFragmentManager().beginTransaction().replace(R.id.addTitle_container,addTitleFragment).commit();

}
}
