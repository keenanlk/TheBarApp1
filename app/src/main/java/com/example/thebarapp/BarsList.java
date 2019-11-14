package com.example.thebarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BarsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bars_list);

        //Click to get to Old Broadway activity
        CardView car = (CardView) findViewById(R.id.card1);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent obIntent = new Intent(getApplicationContext(), OldBroadway.class);
                 startActivity(obIntent);
            }
        });

        //Click to get to Sports Bar Activity
        CardView sports = (CardView) findViewById(R.id.sportsBarCard);
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obIntent = new Intent(getApplicationContext(), SportsBar.class);
                startActivity(obIntent);
            }
        });

        //Click to get to HerdAndHorns activity
        CardView herd = (CardView) findViewById(R.id.HerdHornsCard);
        herd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obIntent = new Intent(getApplicationContext(), HerdAndHorns.class);
                startActivity(obIntent);
            }
        });
    }
}
