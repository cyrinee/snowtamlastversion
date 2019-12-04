package com.example.user.snowtam;

import android.net.http.RequestQueue;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.snowtam.Adapters.SliderAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class decodeactivitytwo extends AppCompatActivity {

    ViewPager viewPager;
    private int dotscount;
    private ImageView[] dots;
    private TextView[] dotss;
    LinearLayout sliderDotspanel;
    List<snowtam> snowtamList;
    SliderAdapter viewPagerAdapter;
    private ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decodeactivitytwo);

        snowtamList = new ArrayList<>();

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //sliderDotspanel = (LinearLayout) findViewById(R.id.dots);

        new JsonTask().execute();
       // adddots();
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_before:
                    toolbar.setTitle("before");
                    return true;

                case R.id.navigation_decode:
                    toolbar.setTitle("Decode");
                    return true;
                case R.id.navigation_settings:
                    toolbar.setTitle("settings");
                    return true;
            }
            return false;
        }
    };


    /* viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

         }

        // @Override
        /* public void onPageSelected(int position) {

             for(int i = 0; i< dotscount; i++){
                 dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
             }

             dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     });

 }*/
    private class JsonTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... params) {
            String str1 = "https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/states/notams/notams-realtime-list?api_key=a59eaf50-0b79-11ea-801d-6bf2a3d2c158&format=json&criticality=&locations=ENBR";
            List<String> listurl = new ArrayList<>();
            String str2 = "https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/states/notams/notams-realtime-list?api_key=a59eaf50-0b79-11ea-801d-6bf2a3d2c158&format=json&criticality=&locations=ENBO";
            String str3 = "https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/states/notams/notams-realtime-list?api_key=a59eaf50-0b79-11ea-801d-6bf2a3d2c158&format=json&criticality=&locations=KJFK";
            String url1 = urlbuild(str1);
            String url2 = urlbuild(str2);
            String url3 = urlbuild(str3);
            listurl.add(url1);
            listurl.add(url2);
            listurl.add(url3);

            return listurl;
        }

        @Override
        protected void onPostExecute(List<String> response) {
            //parse the JSON data and then display
            parseJSON(response);
        }
    }

    private String urlbuild(String ls) {

        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try

        {
            URL url = new URL(ls);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }


            return (stringBuffer.toString());
        } catch (
                Exception ex)

        {
            Log.e("App", "yourDataTask", ex);
            return null;
        } finally

        {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void adddots(){
        dotss=new TextView[4];
        for(int i=0;i<dotss.length;i++){
            dotss[i]=new TextView(this);
            dotss[i].setText(Html.fromHtml("&#8226"));
            dotss[i].setTextSize(35);

            dotss[i].setTextColor(getResources().getColor(R.color.black));
            sliderDotspanel.addView(dotss[i]);
        }


    }
    private void extract(String s,String pattern1,String pattern2){

        Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group(1));
        }}
    private void parseJSON(List<String> data) {
        int k = data.size();
        for (int jj = 0; jj < k; jj++) {
            boolean ss;
            try {
                JSONArray jsonMainNode = new JSONArray(data.get(jj));

                int jsonArrLength = jsonMainNode.length();

                ss = false;
                int i = 0;
                snowtam snowtamm = new snowtam();

                // for(int i=0; i < jsonArrLength; i++) {
                while (((i < jsonArrLength) && !ss)) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    String postTitle = jsonChildNode.getString("all");

                    if (postTitle.contains("SNOWTAM")) {
                        ss = true;
                       // Pattern p = Pattern.compile(Pattern.quote(")") + "(.*?)" + Pattern.quote(")"));
                        //Matcher m = p.matcher(postTitle);
                        int index = postTitle.indexOf("A)");
                        snowtamm.setName(postTitle.substring(index,postTitle.length()));

                    } else {
                        i++;
                    }
                }
                snowtamList.add(snowtamm);

                viewPagerAdapter = new SliderAdapter(snowtamList, decodeactivitytwo.this);
                viewPager.setAdapter(viewPagerAdapter);
            } catch (Exception e) {
                Log.i("App", "Error parsing data" + e.getMessage());

            }
        }
                dotscount = viewPagerAdapter.getCount();
                dots = new ImageView[dotscount];
                for(int f = 0; f < 3; f++){

                    dots[f] = new ImageView(this);
                    dots[f].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.noactive_dot));

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    params.setMargins(8, 0, 8, 0);

                    sliderDotspanel.addView(dots[f], params);

                }

                dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        for(int f = 0; f< dotscount; f++){
                            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.noactive_dot));
                        }

                        dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

              //  viewPager.setAdapter(viewPagerAdapter);

              //  dotscount = viewPagerAdapter.getCount();
              /*  dots = new ImageView[dotscount];
                for(int j = 0; i < dotscount; i++){

                    dots[i] = new ImageView(decodeactivitytwo.this);
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.noactive_dot));

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    params.setMargins(8, 0, 8, 0);

                    sliderDotspanel.addView(dots[i], params);

                }*/

                // dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));



    }
}



