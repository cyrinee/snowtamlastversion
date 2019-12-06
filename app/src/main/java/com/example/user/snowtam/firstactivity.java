
package com.example.user.snowtam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class firstactivity extends AppCompatActivity {

    private Button button;
    String airport1,airport2,airport3;
    EditText Ai,DT,RN;
    private EditText mEditTestnumber1;
    private EditText mEditTestnumber2;
    private EditText mEditTestnumber3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstactivity);




        //setTitle("Activity 2");



        Ai= (EditText)findViewById(R.id.text_view1);
         DT= (EditText)findViewById(R.id.text_view2);
         RN= (EditText)findViewById(R.id.text_view3);



        airport1 = Ai.getText().toString();
        airport2 = DT.getText().toString();
        airport3 = RN.getText().toString();
       /* Ai.setText(" "+aireport);
        DT.setText(" "+dateAndTime);
        RN.setText(" "+runway);*/

        // Slidr.attach(this);

        button = (Button) findViewById(R.id.button_maps);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(getApplicationContext(), decodeactivitytwo.class);

                Intent intentMaps = new Intent(getApplicationContext(), Maps.class);
             //   intent.putExtra("code1", airport1);


                intentMaps.putExtra("airport1", Ai.getText().toString());

                intentMaps.putExtra("airport2", DT.getText().toString());

                intentMaps.putExtra("airport3", RN.getText().toString());


                intent.putExtra("airport1", Ai.getText().toString());

                intent.putExtra("airport2", DT.getText().toString());

                intent.putExtra("airport3", RN.getText().toString());
               // Log.e("App", "helooooooooooooooooooo: " + airport1 );


                //startActivity(intent);
                startActivity(intentMaps);
            }
        });
    }
}
