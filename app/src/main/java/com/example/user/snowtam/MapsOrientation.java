package com.example.user.snowtam;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;



public class MapsOrientation extends AppCompatActivity {
    String code,icao,latt,lon;
    //maps
    private MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maps_orientation);
        TextView tv = (TextView) findViewById(R.id.text);
        code = getIntent().getStringExtra("airport1");
     //   tv.setText(code);
       // code = getIntent().getStringExtra("airport");
            icao = getcode(code);
       // Log.e("App", "decodagesnowtam" + icao );


        try {
            lon=loadlongitude(this, icao);
            Log.e("App", "lon" + lon );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            latt=loadlattitude(this, icao);
            Log.e("App", "lo" + latt );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Maps
        Mapbox.getInstance(this,"pk.eyJ1IjoibWVoZGlyY2QiLCJhIjoiY2szdWFsc2lnMDJuazNucWUydXhtZ2NyMCJ9.5psa9-vGNds3U1V-Tj7FCA");
        //Mapbox.getInstance(this, "pk.eyJ1IjoibWVoZGlyY2QiLCJhIjoiY2szdWFsc2lnMDJuazNucWUydXhtZ2NyMCJ9.5psa9-vGNds3U1V-Tj7FCA");
        setContentView(R.layout.activity_maps_orientation);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                //Ajouter Marker

                MarkerOptions options = new MarkerOptions();

                options.title("n3el bo l3aalam");
                options.position(new LatLng(Double.parseDouble(latt),Double.parseDouble(lon)));
                mapboxMap.addMarker(options);





                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments.


                    }
                });
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    public static String getcode(String cc){
        int index2;
        String bb="";
        if(cc != null){
            index2 =  cc.indexOf("B)");
            bb=cc.substring(3,index2-1);
            //a.setText(snowtamm.getName());

          //  Log.e("App", "aa:" + bb );
        }
        return bb;
    }


    public static String loadlongitude(Context context,String code3) throws JSONException {
        String longg=" " ;

        // Log.e("App", "Sucecodecessssssssssssssssssssssssss:" + code3 );
        JSONArray array = new JSONArray(loadJSONFromAsset(context, "airport.json"));
        int j=0;

        for(int i=0;i<array.length();i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            String codee = jsonObject.getString("ICAO");
            longg = jsonObject.getString("Longitude");

            // Log.e("App", "decodagesnowtam" + codee );
            // Log.e("Avfffffffffffpp", "gfhgdhjdgggdg" + airport );

            if (code3.equals(codee)) {
                longg = jsonObject.getString("Longitude");

               // Log.e("App", "long: " + longg );
            }


        }


        return longg;

    }
    public static String loadlattitude(Context context,String code3) throws JSONException {
        String latt=" " ;
        GsonBuilder builder = new GsonBuilder();
        // Log.e("App", "Sucecodecessssssssssssssssssssssssss:" + code3 );
        Gson gson = builder.create();
        JSONArray array = new JSONArray(loadJSONFromAsset(context, "airport.json"));
        int j=0;

        for(int i=0;i<array.length();i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            String codee = jsonObject.getString("ICAO");


            if (code3.equals(codee)) {
                latt = jsonObject.getString("Latitude");

            }


        }


        return latt;

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
