
package com.example.user.snowtam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class firstactivity extends AppCompatActivity {

    private Button button;
    String airport1,airport2,airport3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstactivity);

        //setTitle("Activity 2");



       // EditText Ai= findViewById(R.id.text_view1);
        EditText DT= findViewById(R.id.text_view2);
        EditText RN= findViewById(R.id.text_view3);



      //  airport1 = Ai.getText().toString();
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
             //   intent.putExtra("code1", airport1);
                intent.putExtra("airport1", airport1);

                intent.putExtra("airport2", airport2);

                intent.putExtra("airport3", airport3);

                startActivity(intent);
            }
        });
    }
}
