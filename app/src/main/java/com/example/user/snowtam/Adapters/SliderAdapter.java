package com.example.user.snowtam.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.viewpager.widget.PagerAdapter;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.snowtam.MapsOrientation;
import com.example.user.snowtam.R;
import com.example.user.snowtam.decodagesnowtam;
import com.example.user.snowtam.firstactivity;
import com.example.user.snowtam.snowtam;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    Context context;
    Button button;

    LayoutInflater layoutInflater;
    private List<snowtam> listsnowtam;
    public SliderAdapter(List listsnowtam,Context context){
        this.context=context;
        this.listsnowtam=listsnowtam;
    }


//Arrays
    public int[] slide_images={
     R.drawable.eee,
        R.drawable.dd,
        R.drawable.ee,
        R.drawable.dd,
};


    @Override
    public int getCount() {
return 3;    }

    @Override
    public boolean isViewFromObject(@NonNull final View view, @NonNull final Object o) {



return view==(RelativeLayout) o;    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final snowtam snowtamm = listsnowtam.get(position);
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView avion = (ImageView) view.findViewById(R.id.avionImageView);
        TextView a = (TextView) view.findViewById(R.id.text);

        a.setMovementMethod(new ScrollingMovementMethod());
       // avion.setImageResource(slide_images[position]);
         if (snowtamm.getName()==null){
             a.setText("No data available");
         }else{

            a.setText(snowtamm.getName());}
        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_before:
                        Intent a = new Intent( context,firstactivity.class);
                        context.startActivity(a);
                        return true;

                    case R.id.navigation_decode:
                        Intent intent = new Intent(context,MapsOrientation.class);
                        //   intent.putExtra("code1", airport1);
                        intent.putExtra("airport1", snowtamm.getName());
                        context.startActivity(intent);
                        return true;
                    case R.id.navigation_settings:
                        return true;
                }
                return false;
            }
        });
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( view.getContext(),decodagesnowtam.class);
                intent.putExtra("airport", snowtamm.getName());
                context.startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);

    }



}

