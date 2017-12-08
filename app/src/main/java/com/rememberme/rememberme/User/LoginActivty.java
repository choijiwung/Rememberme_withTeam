package com.rememberme.rememberme.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blackbutterfly.mytab.MainActivity;
import com.blackbutterfly.mytab.Network.APINetwork;
import com.blackbutterfly.mytab.Network.ApplicationController;
import com.blackbutterfly.mytab.R;
import com.blackbutterfly.mytab.User.Results.LoginResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivty extends AppCompatActivity {
    APINetwork network;
    Button loginButton;
    Button SignupButton;
    EditText idText, passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);
        network = ApplicationController.getInstance().getApiNetwork();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        idText = (EditText)findViewById(R.id.idText);
        passwordText = (EditText)findViewById(R.id.passwordText);

        idText.setText("whdqhd5402@gmail.com");
        passwordText.setText("123123");

        loginButton = (Button) findViewById(R.id.Loginbtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                String email = idText.getText().toString();
                String password = passwordText.getText().toString();
                login(email, password);
            }
        });

        SignupButton = (Button) findViewById(R.id.Signbtn);
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signupIntent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(signupIntent);
            }
        });

    }

    private void login(String email, String password) {
        User user = new User(email, password);
        Call<LoginResult> LoginResultCall = network.getLoginResult(user);
        LoginResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.isSuccessful()) {
                    LoginResult loginResult = response.body();
                    Log.i("Sign", "Login Success / msg : ".concat(loginResult.msg).concat(", token : ".concat(loginResult.token)));

                    Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loginIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivty.this, "아이디와 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    Log.i("Sign", "code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.i("Sign", t.getMessage());
            }
        });
    }

}
