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
String code,code2,date2,runaway,icao,date,champsF,F;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decodagesnowtam);
         tv = (TextView) findViewById(R.id.text);

        code = getIntent().getStringExtra("airport");
      //Log.e("App", "code " + code );

        if(code!=null) {
            icao = getcode(code);
            date=getdate(code);
            runaway=getranaway(code);
            champsF=getF(code);
            Log.e("App", "code " + champsF );
F=parseF(champsF);
            try {
                 date2=parsedate(date);
               // Log.e("App", "date " + date2 );

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        try {
             code2=loadairport(this,icao.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv.setText(code2+ "\n");
        tv.append(date2 + "\n");
        tv.append(runaway+"\n");
        tv.append(F );



    }
    public  String getcode(String cc){
        int index2;
        String bb="";
        if(cc != null){
            index2 =  cc.indexOf("B)");
            bb=cc.substring(3,index2-1);
            //a.setText(snowtamm.getName());
           // Log.e("App", "aa:" + bb );

            //Log.e("App", "aa:" + bb.length() );
            }
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
    public static String getF(String cc){
        int index2,index3;
        String bb="";

        if(cc != null){

            index2 =  cc.indexOf("F)");
            index3=cc.indexOf("G)");
            bb=cc.substring(index2+2,index3);
            //a.setText(snowtamm.getName());
            // Log.e("App", "aa:" + bb );
        }
        return bb;

    }
    public  static String parseF(String s){

        String b=null,bb="",bbb="";
        Log.e("App", "aa:" + s.substring(1,2) );
        Log.e("App", "aa:" + s.substring(3,4) );
        Log.e("App", "aa:" + s.substring(5,6) );
        if (s.substring(1,2)=="0"){

            b="CLEAR AND DRY";
        }
        else if(s.substring(1,2)=="1") {

             b=  "DAMP";
        }
        else if(s.substring(1,2)=="2") {
            Log.e("App", "aa:" + b );

           b=  "WET or WATER PATCHES";
        }
        else if(s.substring(1,2)=="3") {

             b=  "RIME OR FROST COVERED";
        }
        else if(s.substring(1,2)=="4") {

             b=  "DRY SNOW";
        }
        else if(s.substring(1,2)=="5") {

             b=  "WET  SNOW";
        }
        else if(s.substring(1,2)=="6") {

             b=  "SLUSH";
        }
        else if(s.substring(1,2)=="7") {

             b=  "ICE";
        }
        else if(s.substring(1,2)=="8") {

             b=  "COMPACTED OR ROLLED SNOW";
        }

        else if(s.substring(1,2)=="9") {

             b=  "FROZEN RUTS";
        }
        if (s.substring(3,4)=="0"){
             bb="CLEAR AND DRY";
        }
        else if(s.substring(3,4)=="1") {

             bb=  "DAMP";
        }
        else if(s.substring(3,4)=="2") {

             bb=  "WET or WATER PATCHES";
        }
        else if(s.substring(3,4)=="3") {

             bb=  "RIME OR FROST COVERED";
        }
        else if(s.substring(3,4)=="4") {

             bb=  "DRY SNOW";
        }
        else if(s.substring(3,4)=="5") {

             bb=  "WET  SNOW";
        }
        else if(s.substring(3,4)=="6") {

             bb=  "SLUSH";
        }
        else if(s.substring(3,4)=="7") {

             bb=  "ICE";
        }
        else if(s.substring(3,4)=="8") {

             bb=  "COMPACTED OR ROLLED SNOW";
        }

        else if(s.substring(3,4)=="9") {

             bb=  "FROZEN RUTS";
        }
        if (s.substring(5,6)=="0"){
             bbb="CLEAR AND DRY";
        }
        else if(s.substring(5,6)=="1") {

             bbb=  "DAMP";
        }
        else if(s.substring(5,6)=="2") {

             bbb=  "WET or WATER PATCHES";
        }
        else if(s.substring(5,6)=="3") {

             bbb=  "RIME OR FROST COVERED";
        }
        else if(s.substring(5,6)=="4") {

             bbb=  "DRY SNOW";
        }
        else if(s.substring(5,6)=="5") {

             bbb=  "WET  SNOW";
        }
        else if(s.substring(5,6)=="6") {

             bbb=  "SLUSH";
        }
        else if(s.substring(5,6)=="7") {

             bbb=  "ICE";
        }
        else if(s.substring(5,6)=="8") {

             bbb=  "COMPACTED OR ROLLED SNOW";
        }

        else if(s.substring(5,6)=="9") {

             bbb=  "FROZEN RUTS";
        }

        String str="F. "+ b+ "/"+bb+"/"+bbb;

        return str;

    }

    public static String loadairport(Context context,String code3) throws JSONException {
       String airport=" " ;
           // GsonBuilder builder = new GsonBuilder();
       //Log.e("App", "Sucecodecessssssssssssssssssssssssss" + code3 );
      //  Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "airport.json"));
            int j=0;

            for(int i=0;i<array.length();i++) {
                JSONObject J = array.getJSONObject(i);
                 String codee =  J.getString("ICAO");
             // Log.e("App", "decodagesnowtam" + codee.length() );
               // Log.e("Avfffffffffffpp", "code" + code3 );

                if (codee.equals(code3)) {
                     airport = J.getString("Name");

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
        //Log.e("App", "ff " + code );

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmm");
        Date date = dateFormat.parse(code);
      //  Log.e("App", "icao " + date );

        StringBuilder sb = new StringBuilder("B. ");
        sb.append( date);
        String str = sb.toString();
        return str;    }
}
