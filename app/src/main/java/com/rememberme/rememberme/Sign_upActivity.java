package com.rememberme.rememberme;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.rememberme.rememberme.R.id.emailText;

public class Sign_upActivity extends AppCompatActivity implements View.OnClickListener  {
    EditText etemail;
    EditText etpassword;
    EditText etname;
    EditText etpasswordconfirm;
    Button btnPost;
    TextView tvIsConnected;
    User user;
    static    String strJson = "";
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        final String url = "http://70.12.50.58:3000/users";
        //레이아웃에서 뷰 찾기

        etemail = (EditText) findViewById(emailText);
        etpassword = (EditText) findViewById(R.id.passwordText);
        etpasswordconfirm = (EditText) findViewById(R.id.PasswordConfirmText);
        tvIsConnected = (TextView) findViewById(R.id.textView);
        textView = (TextView) findViewById(R.id.textView3);
        btnPost = (Button) findViewById(R.id.button);


        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }

        // add click listener to Button "POST"
        btnPost.setOnClickListener(this);

    }

    public static String POST(String url, User user){
        InputStream is = null;
        String result = "";
        try {
            URL urlCon = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();

            String json = "";

            // build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("email", user.getEmail());
            jsonObject.accumulate("name", user.getName());
            jsonObject.accumulate("password", user.getPassword());
            Log.d("aa","json,pass :" + user.getPassword_confirmation());
            jsonObject.accumulate("password_confirmation", user.getPassword_confirmation());

            // convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(user);

            // Set some headers to inform server about the type of the content
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestProperty("Content-type", "application/json");

            // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
            httpCon.setDoOutput(true);
            // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
            httpCon.setDoInput(true);

            OutputStream os = httpCon.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.flush();
            // receive response as inputStream
            try {
                is = httpCon.getInputStream();
                // convert inputstream to string
                if(is != null)
                    result = convertInputStreamToString(is);
                else
                    result = "Did not work!";
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                httpCon.disconnect();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public void onClick(View view) {

        switch(view.getId()){
            case R.id.button:
                if(!validate())
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                else {
                    // call AsynTask to perform network operation on separate thread
                    HttpAsyncTask httpTask = new HttpAsyncTask(Sign_upActivity.this);
                    Log.d("aa", "name : " + etname.getText().toString());
                    Log.d("aa", "email : " + etemail.getText().toString());
                    Log.d("aa", "password : " + etpassword.getText().toString());
                    Log.d("aa", "password_confirmation : " + etpasswordconfirm.getText().toString());
                    httpTask.execute("http://70.12.50.58:3000/users/signup", etemail.getText().toString(), etname.getText().toString(), etpassword.getText().toString(), etpasswordconfirm.getText().toString());
//                    httpTask.execute("http://70.12.50.58:3000/users/signup");

                }
                break;
        }

    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        private   Sign_upActivity mainAct;

        HttpAsyncTask(Sign_upActivity sign_upActivity) {
            this.mainAct = sign_upActivity;
        }
        @Override
        protected String doInBackground(String... urls) {

            user = new User();
            user.setEmail(urls[1]);
            user.setPassword(urls[2]);
            user.setPassword_confirmation(urls[3]);
            Log.d("aa",user.toString());
            return POST(urls[0], user);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            strJson  = result;
            Log.d("aa",result);
            mainAct.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mainAct, "success!", Toast.LENGTH_LONG).show();
                    try {
                        JSONArray json = new JSONArray(strJson);
                        mainAct.textView.setText(json.toString(1));
                        Toast.makeText(mainAct, json.toString(0)+", " + json.toString(1)+json.toString(2), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private boolean validate(){
        if(etemail.getText().toString().trim().equals(""))
            return false;
        else if(etpassword.getText().toString().trim().equals(""))
            return false;
        else if(etname.getText().toString().trim().equals(""))
            return false;
        else if(etpasswordconfirm.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}