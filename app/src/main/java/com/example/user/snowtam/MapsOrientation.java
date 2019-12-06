package com.example.user.snowtam;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MapsOrientation extends AppCompatActivity {
String code,icao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_orientation);
        code = getIntent().getStringExtra("airport");
            icao = getcode(code);

    }
    public static String getcode(String cc){
        int index2;
        String bb="";
        if(cc != null){
            index2 =  cc.indexOf("B)");
            bb=cc.substring(3,index2);
            //a.setText(snowtamm.getName());

            Log.e("App", "aa:" + bb );}
        return bb;
    }


    public static String loadairport(Context context,String code3) throws JSONException {
        String airport=" " ;
        GsonBuilder builder = new GsonBuilder();
        // Log.e("App", "Sucecodecessssssssssssssssssssssssss:" + code3 );
        Gson gson = builder.create();
        JSONArray array = new JSONArray(loadJSONFromAsset(context, "airport.json"));
        int j=0;

        for(int i=0;i<array.length();i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            String codee = jsonObject.getString("ICAO");
            // Log.e("App", "decodagesnowtam" + codee );
            // Log.e("Avfffffffffffpp", "code" + code3 );

            if (code3.equals(codee)) {
                airport = jsonObject.getString("Name");

                Log.e("App", "airport: " + airport );
            }
            //System.out.println("hellooooooo"+ code.equals(codee));


        }
        StringBuilder sb = new StringBuilder("A. ");

        sb.append(code3 );
        sb.append(" " +airport);
        String str = sb.toString();

        return str;

    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            // Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
