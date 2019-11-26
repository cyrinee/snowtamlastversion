package com.example.user.snowtam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class testactivity extends AppCompatActivity {
Button btn;
EditText txtJson;
    ProgressDialog pd;
    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testactivity);
        btn =  findViewById(R.id.btn);
        txtJson =(EditText)  findViewById(R.id.code);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                code = txtJson.getText().toString();
                Intent intent = new Intent(getApplicationContext(), decodeActivity.class);
                startActivityForResult(intent, code);
                new JsonTask().execute();
            }
        });
    }
    protected class JsonTask extends AsyncTask<Void, Void, JSONObject>
    {
        @Override
        protected JSONObject doInBackground(Void... params)
        {


            String str=" https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/states/notams/notams-realtime-list?api_key=a59eaf50-0b79-11ea-801d-6bf2a3d2c158&format=json&criticality=&locations="+code ;
          //  Log.e("App", "Sucecodecess: " + str );

            URLConnection urlConn = null;
            BufferedReader bufferedReader = null;
            try
            {
                URL url = new URL(str);
                urlConn = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuffer.append(line);
                }

                return new JSONObject(stringBuffer.toString());
            }
            catch(Exception ex)
            {
                Log.e("App", "yourDataTask", ex);
                return null;
            }
            finally
            {
                if(bufferedReader != null)
                {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(JSONObject response)
        {
            if(response != null)
            {
                try {

                    Log.e("App", "Success: " + response.getString("yourJsonElement") );




                } catch (JSONException ex) {
                    Log.e("App", "Failure", ex);
                }
            }
        }}}
