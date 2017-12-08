package com.rememberme.rememberme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import static android.widget.Toast.makeText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText idText,passwordText;
    Button loginButton,signButton;
    static    String strJson = "";
    User user;
    String token;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        final String url = "http://70.12.50.58:3000/users";
        //레이아웃에서 뷰 찾기

        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        loginButton = (Button) findViewById(R.id.loginButton);

        signButton = (Button) findViewById(R.id.Signbtn);

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sign_upIntent = new Intent(getApplicationContext(), Sign_upActivity.class);
                startActivity(Sign_upIntent);
            }
        });

        if(MyHttpAsyncTask.isConnected(this)){
//            tvIsConnected.setBackgroundColor(0xFF00CC00);
//            tvIsConnected.setText("You are conncted");
        }
        else{
//            tvIsConnected.setText("You are NOT conncted");
        }

        // add click listener to Button "POST"
        loginButton.setOnClickListener(this);

    }



    public void onClick(View view) {

        switch(view.getId()){
            case R.id.loginButton:
                if(!validate())
                    makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                else {
                    // call AsynTask to perform network operation on separate thread
                    MyHttpAsyncTask httpTask = new MyHttpAsyncTask(LoginActivity.this);
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("url", "http://70.12.50.58:3000/users/signin");
                    params.put("email", idText.getText().toString());
                    params.put("password", passwordText.getText().toString());

                    httpTask.execute(params);
                    Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(loginIntent);
                }
                break;
        }

    }
    private boolean validate(){
        if(idText.getText().toString().trim().equals(""))
            return false;
        else if(passwordText.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }







}