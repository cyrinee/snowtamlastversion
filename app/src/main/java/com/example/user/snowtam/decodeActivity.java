package com.example.user.snowtam;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class decodeActivity extends AppCompatActivity {

    String codeurl;
    TextView tv;
    ListView listView;
    ArrayList<String> tutorialList = new ArrayList<String>();
    snowtam snowtamm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);
        // tv = (TextView) findViewById(R.id.text_id);
        codeurl = getIntent().getStringExtra("code");
        String str=" https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/states/notams/notams-realtime-list?api_key=a59eaf50-0b79-11ea-801d-6bf2a3d2c158&format=json&criticality=&locations="+codeurl ;

        new JsonTask().execute();



    }

    private class JsonTask extends AsyncTask<Void, Void, String >{

        @Override
        protected String doInBackground(Void... params) {
            String str=" https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/states/notams/notams-realtime-list?api_key=a59eaf50-0b79-11ea-801d-6bf2a3d2c158&format=json&criticality=&locations="+codeurl ;
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


                return (stringBuffer.toString());
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
        protected void onPostExecute(String  response) {
            //parse the JSON data and then display
            parseJSON(response);
        }}


        private String convertInputStreamToString(InputStream inputStream) throws IOException{
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
            return result;

        }

        private void parseJSON(String data){
            boolean snowtam;
            try{
                JSONArray jsonMainNode = new JSONArray(data);

                int jsonArrLength = jsonMainNode.length();

                 snowtam = false;
                 int i=0;
               // for(int i=0; i < jsonArrLength; i++) {
                while ( ((i<jsonArrLength)&&!snowtam)){
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    String postTitle = jsonChildNode.getString("all");

                        if (postTitle.contains("SNOWTAM")) {
                            snowtam=true;
                           snowtamm.setName(postTitle);

                        }
                else{i++;}}



                // Get ListView object from xml
                listView = (ListView) findViewById(R.id.list);

                // Define a new Adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(decodeActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, tutorialList);

                // Assign adapter to ListView
                listView.setAdapter(adapter);

            }catch(Exception e){
                Log.i("App", "Error parsing data" +e.getMessage());

            }
        }


    private void parseJSON2(String data){
        boolean snowtam;
        try{
            JSONArray jsonMainNode = new JSONArray(data);

            int jsonArrLength = jsonMainNode.length();

            snowtam = false;
            int i=0;
            // for(int i=0; i < jsonArrLength; i++) {
            while ( ((i<jsonArrLength)&&!snowtam)){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String postTitle = jsonChildNode.getString("all");

                if (postTitle.contains("SNOWTAM")) {
                    snowtam=true;
                    tutorialList.add(postTitle);

                }
                else{i++;}}



            // Get ListView object from xml
            listView = (ListView) findViewById(R.id.list);

            // Define a new Adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(decodeActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, tutorialList);

            // Assign adapter to ListView
            listView.setAdapter(adapter);

        }catch(Exception e){
            Log.i("App", "Error parsing data" +e.getMessage());

        }
    }

    }


