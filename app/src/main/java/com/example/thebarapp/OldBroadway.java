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

public class OldBroadway extends AppCompatActivity {
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
        foodRate = findViewById(R.id.foodRate);
        drinksRate = findViewById(R.id.drinkRate);
        atmosphereRate = findViewById(R.id.atmosphereRate);
        overallRate = findViewById(R.id.overallRate);

        Button reviewBtn = (Button)findViewById(R.id.button4);

        //Set up button press
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ReviewActivity.class);
                //This is hardcoded bar name
                startIntent.putExtra("barName", "The Old Broadway");
                startActivity(startIntent);
            }
        });

        // DB url
        String url = "http://ndsucsci415.herokuapp.com/api/reviews/?Bar=The+Old+Broadway";

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
