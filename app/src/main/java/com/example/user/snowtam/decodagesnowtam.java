package com.example.user.snowtam;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.snowtam.Adapters.SliderAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class decodagesnowtam extends AppCompatActivity {
String code,code2,date2,runaway,icao,date;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decodagesnowtam);
         tv = (TextView) findViewById(R.id.text);

        code = getIntent().getStringExtra("airport");
        if(code!=null) {
            icao = getcode(code);
            date=getdate(code);
            runaway=getranaway(code);
            try {
                 date2=parsedate(date);
               // Log.e("App", "date " + date2 );

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        try {
             code2=loadairport(this,icao);
           // Log.e("App", "hyhyhy " + code2 );

          // Log.e("App", "icao " + icao );
           // Log.e("App", "alo " + code2 );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv.setText(code2+ "\n");
        tv.append(date2 + "\n");
        tv.append(runaway);


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
    public static String getdate(String cc){
        int index2,index3;
        String bb="";

        if(cc != null){

            index2 =  cc.indexOf("B)");
            index3=cc.indexOf("C)");
            bb=cc.substring(index2+2,index3);
            //a.setText(snowtamm.getName());
           // Log.e("App", "aa:" + bb );
        }
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
    public static String getranaway(String code){
        int index2,index3;
        String bb="";

        if(code != null){

            index2 =  code.indexOf("C)");
            index3=code.indexOf("F)");
            bb="C. " +code.substring(index2+2,index3-1)+ " Runway";


            //a.setText(snowtamm.getName());

           // Log.e("App", "aa:" + bb );
            }
        return bb;

    }

    public static String parsedate(String code) throws ParseException {
        Log.e("App", "ff " + code );

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmm");
        Date date = dateFormat.parse(code);
        Log.e("App", "icao " + date );

        StringBuilder sb = new StringBuilder("B. ");
        sb.append( date);
        String str = sb.toString();
        return str;    }
}
