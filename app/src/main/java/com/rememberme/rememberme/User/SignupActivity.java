package com.rememberme.rememberme.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.blackbutterfly.mytab.MainActivity;
import com.blackbutterfly.mytab.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_signup);

        Button signCompleteButton = (Button) findViewById(R.id.button3);

        signCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signComplete = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(signComplete);
            }
        });


    }
}
