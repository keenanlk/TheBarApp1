package com.example.thebarapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.android.volley.*;
import org.json.*;
import com.android.volley.toolbox.*;
import com.google.gson.JsonIOException;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.opencensus.internal.StringUtils;

public class OldBroadway extends AppCompatActivity {
    //creating the variables
    private TextView textData;
    private static final String TAG = "OldBroadway";
    private RatingBar foodRate;
    private RatingBar drinksRate;
    private RatingBar atmosphereRate;
    private RatingBar overallRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_broadway);

        //Set variables
        textData = (TextView)findViewById(R.id.commentTxt);
        textData.setText("Reviews:\n");
        foodRate = findViewById(R.id.foodRate);
        drinksRate = findViewById(R.id.drinkRate);
        atmosphereRate = findViewById(R.id.atmosphereRate);
        overallRate = findViewById(R.id.overallRate);

        Button reviewBtn = (Button)findViewById(R.id.button4);

        //Set up button press to write a review
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ReviewActivity.class);
                //This is hardcoded bar name
                startIntent.putExtra("barName", "The Old Broadway");
                startActivity(startIntent);
                finish();
            }
        });

        // DB url
        String url = "http://ndsucsci415.herokuapp.com/api/reviews/?Bar=The+Old+Broadway";

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
