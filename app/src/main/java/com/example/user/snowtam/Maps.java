package com.example.user.snowtam;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Maps extends AppCompatActivity {

    ArrayList<String> numberList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();

        String code1 = intent.getStringExtra("airport1");
        String code2 = intent.getStringExtra("airport2");
        String code3 = intent.getStringExtra("airport3");

        TextView textcode = findViewById(R.id.textViewMaps);
        textcode.setText("  " + code1 + " " + code2 + code3);


    }

    public void get_json() {
        String json;
        try {
            InputStream is = getAssets().open("airport.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read();
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i=0; i<jsonArray.length();i++){

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }


    }
}
