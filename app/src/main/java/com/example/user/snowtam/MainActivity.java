package com.example.user.snowtam;

import android.os.Bundle;
import androidx.core.view.GestureDetectorCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.snowtam.Adapters.SliderAdapter;

import java.util.List;

//import android.support.v4.app.GestureDetectorCompat;
public class MainActivity extends AppCompatActivity {
private ViewPager viewpage;
private LinearLayout linear;
private  SliderAdapter SliderAdapter;
    private GestureDetectorCompat mGestureDetector;
    List<snowtam> snowtamList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetector = new GestureDetectorCompat(this, new GestureListner());
       // viewpage = (ViewPager) findViewById(R.id.viewpagerr);
        //linear = (LinearLayout) findViewById(R.id.dotsss);
      //  SliderAdapter=new SliderAdapter(this);
        viewpage.setAdapter(SliderAdapter);

    }















    private class GestureListner extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Toast.makeText(MainActivity.this,"Swipe up",Toast.LENGTH_SHORT).show();
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(MainActivity.this,"Swipe up",Toast.LENGTH_SHORT).show();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Toast.makeText(MainActivity.this,"Swipe up",Toast.LENGTH_SHORT).show();
            return super.onSingleTapConfirmed(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
