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
        txtJson =(EditText)  findViewById(R.id.code1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                code = txtJson.getText().toString();
                Intent intent = new Intent(getApplicationContext(), decodeActivity.class);
                intent.putExtra("code", code);
                startActivity(intent);
            }
        });
    }
   }
