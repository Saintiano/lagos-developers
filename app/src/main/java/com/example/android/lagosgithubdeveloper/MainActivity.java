package com.example.android.lagosgithubdeveloper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button findDevelopers;
    Button viewDevelopers;
    Intent intent, intent2;

    ViewPager viewPager;
    SliderAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new SliderAdapter(this);
        viewPager.setAdapter(adapter);
        addListenerOnButton();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000,4000);

    }

    public void addListenerOnButton(){



        final Context context = this;

        findDevelopers = (Button) findViewById(R.id.find);
        viewDevelopers = (Button) findViewById(R.id.pull);

        findDevelopers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, DeveloperFinder.class);
                startActivity(intent);
            }
        });

        viewDevelopers.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                intent2 = new Intent(MainActivity.this, LagosActivity.class);
                startActivity(intent2);
            }

        });
    }


    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(2);
                    }
                    else if (viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    }
                    else if (viewPager.getCurrentItem() == 3){
                        viewPager.setCurrentItem(1);
                    }
                    else if (viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(4);
                    }
                    else if (viewPager.getCurrentItem() == 4){
                        viewPager.setCurrentItem(5);
                    }
                    else {
                        viewPager.setCurrentItem(1);
                    }

                }
            });

        }
    }

}
