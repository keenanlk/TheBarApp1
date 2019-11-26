package com.example.thebarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SportsBar extends AppCompatActivity {

    private RatingBar foodRate;
    private RatingBar drinksRate;
    private RatingBar atmosphereRate;
    private RatingBar overallRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_bar);
        foodRate = findViewById(R.id.foodRate);
        drinksRate = findViewById(R.id.drinkRate);
        atmosphereRate = findViewById(R.id.atmosphereRate);
        overallRate = findViewById(R.id.overallRate);

        Button reviewBtn = (Button)findViewById(R.id.button4);

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(startIntent);
            }
        });

        String url = "http://10.0.2.2:8000/api/reviews/?Bar=The+Sports+Bar";

        //Craft the request we send to the backend
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (url, new Response.Listener<JSONArray>() {
                    // This is where the logic goes for after you recieve a response
                    public void onResponse(JSONArray response) {
                        float overallRating = 0;
                        float drinksRating = 0;
                        float foodRating = 0;
                        float atmosphereRating = 0;

                        // Loop through arrays and calculate
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject row = response.getJSONObject(i);
                                //Sum up stars for each review
                                drinksRating += row.getInt("Drinks");
                                foodRating += row.getInt("Food");
                                atmosphereRating += row.getInt("Atmosphere");
                            }
                            catch (JSONException e){}
                        }
                        //Divide by total number of reviews to get average
                        drinksRating = drinksRating / response.length();
                        foodRating = foodRating / response.length();
                        atmosphereRating = atmosphereRating / response.length();
                        overallRating = (drinksRating + foodRating + atmosphereRating)/3;

                        // Set the stars
                        foodRate.setRating(foodRating);
                        drinksRate.setRating(drinksRating);
                        atmosphereRate.setRating(atmosphereRating);
                        overallRate.setRating(overallRating);
                    }
                    //Make sure backend doesn't throw errors
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e("GetRequest", error.toString());
                    }
                });

        //Sends out the actual request
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
