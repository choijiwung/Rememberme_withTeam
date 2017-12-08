package com.rememberme.rememberme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

import static com.rememberme.rememberme.R.id.emailText;

public class Sign_upActivity extends AppCompatActivity implements View.OnClickListener  {
    EditText idText, passwordText, passwordconfirmText;
    Button btnSignup;
    User user;
    static    String strJson = "";
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        final String url = "http://70.12.50.58:3000/users";
        //레이아웃에서 뷰 찾기

        idText = (EditText) findViewById(emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        passwordconfirmText = (EditText) findViewById(R.id.PasswordConfirmText);
        btnSignup = (Button) findViewById(R.id.btnSignup);


        // check if you are connected or not
        if(MyHttpAsyncTask.isConnected(this)){
//            tvIsConnected.setBackgroundColor(0xFF00CC00);
//            tvIsConnected.setText("You are conncted");
        }
        else{
//            tvIsConnected.setText("You are NOT conncted");
        }

        // add click listener to Button "POST"
        btnSignup.setOnClickListener(this);

    }

    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btnSignup:
                if(!validate())
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                else {
                    // call AsynTask to perform network operation on separate thread
                    final MyHttpAsyncTask httpTask = new MyHttpAsyncTask(Sign_upActivity.this);
                    httpTask.setResultRunnable(new Runnable() {
                        @Override
                        public void run() {
                            String strJson = httpTask.getResult();
                            Log.i("aaaResult", strJson);
                            Toast.makeText(Sign_upActivity.this, strJson, Toast.LENGTH_LONG).show();

                            try {
                                JSONArray json = new JSONArray(strJson);
                                Toast.makeText(Sign_upActivity.this, json.toString(0)+", " + json.toString(1), Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("url", "http://70.12.50.58:3000/users/signup");
                    params.put("email", idText.getText().toString());
                    params.put("password", passwordText.getText().toString());
                    params.put("password_confirmation", passwordconfirmText.getText().toString());
                    httpTask.execute(params);

                    Intent Sign_upIntent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(Sign_upIntent);
                }
                break;
        }

    }

    private boolean validate(){
        if(idText.getText().toString().trim().equals(""))
            return false;
        else if(passwordText.getText().toString().trim().equals(""))
            return false;
        else if(passwordconfirmText.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }

}