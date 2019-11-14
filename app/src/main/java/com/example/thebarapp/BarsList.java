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

        CardView car = (CardView) findViewById(R.id.card1);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent obIntent = new Intent(getApplicationContext(), OldBroadway.class);
                 startActivity(obIntent);
            }
        });
    }
}
