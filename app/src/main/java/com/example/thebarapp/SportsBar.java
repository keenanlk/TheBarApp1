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
    //creating the variables
    private RatingBar foodRate;
    private RatingBar drinksRate;
    private RatingBar atmosphereRate;
    private RatingBar overallRate;
    private TextView textData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_bar);
        //initializing the variables
        textData = (TextView)findViewById(R.id.commentTxt);
        textData.setText("Reviews:\n");
        foodRate = findViewById(R.id.foodRate);
        drinksRate = findViewById(R.id.drinkRate);
        atmosphereRate = findViewById(R.id.atmosphereRate);
        overallRate = findViewById(R.id.overallRate);

        //Button to write a review on the bar
        Button reviewBtn = (Button)findViewById(R.id.button4);

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ReviewActivity.class);
                startIntent.putExtra("barName", "Sports Bar");
                startActivity(startIntent);
                finish();
            }
        });

        // DB url
        String url = "http://ndsucsci415.herokuapp.com/api/reviews/?Bar=Sports+Bar";

        //Craft the request we send to the backend
        //Determine what to do with response
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (url, new Response.Listener<JSONArray>() {

                    // This is where the logic goes for after you receive a response
                    public void onResponse(JSONArray response) {
                        float overallRating = 0;
                        float drinksRating = 0;
                        float foodRating = 0;
                        float atmosphereRating = 0;
                        String comment;

                        // Loop through Response reviews
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                //Cast to JSONObj
                                JSONObject row = response.getJSONObject(i);

                                //Get values from Obj
                                float currentDrinkRating = row.getInt("Drinks");
                                float currentFoodRating = row.getInt("Food");
                                float currentAtmosphereRating = row.getInt("Atmosphere");
                                comment = row.getString("Comment");


                                //Sum up stars for each review
                                drinksRating += currentDrinkRating;
                                foodRating += currentFoodRating;
                                atmosphereRating += currentAtmosphereRating;

                                //Format each review to display
                                textData.append("---------------------------------------------------");
                                textData.append("\nFood: " + currentFoodRating + "\n");
                                textData.append("Drinks: " + currentDrinkRating + "\n");
                                textData.append("Atmosphere: " + currentAtmosphereRating + "\n");
                                textData.append("Comment: " +  comment + "\n");
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
