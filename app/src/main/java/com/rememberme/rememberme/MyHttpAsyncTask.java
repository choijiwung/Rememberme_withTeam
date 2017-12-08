package com.rememberme.rememberme;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
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
import java.util.HashMap;

import static android.widget.Toast.makeText;

/**
 * Created by JW on 2017-12-08.
 */

public class MyHttpAsyncTask extends AsyncTask<HashMap<String, Object>, Void, String> {

    private final Context context;

        MyHttpAsyncTask(Context context) {
            this.context = context;
        }


    @Override
    protected String doInBackground(HashMap<String, Object>... params) {
        String url = params[0].get("url").toString();
        params[0].remove("url");
        return POST(url, params[0]);
        //return null;
    }

    //        @Override
//        protected String doInBackground(String... urls) {
//
//            user = new User();
//            user.setEmail(urls[1]);
//            user.setPassword(urls[2]);
////            user.setToken(urls[3]);
////            user.setToken(urls[4]);
//            return POST(urls[0], user);
//        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            final String strJson  = result;
            Log.d("aa",result);
//            token = result;
//            Log.d("aa",token.toString());
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
//
                    makeText(context, "success!", Toast.LENGTH_LONG).show();

                    try {
                        JSONArray json = new JSONArray(strJson);
                        makeText(context, json.toString(0)+", " + json.toString(1), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    public static boolean isConnected(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
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
    public static String POST(String url, HashMap<String, Object> params){
        InputStream is = null;
        String result = "";
        try {
            URL urlCon = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();

            String json = "";

            // build jsonObject
            JSONObject jsonObject = new JSONObject();

            for(String key:params.keySet()){
                Object value = params.get(key);
                jsonObject.accumulate(key, value);
            }

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
                Log.i("aa",is.toString());
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
}
