package com.example.thebarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReviewActivity extends AppCompatActivity {
    //creating keys for the review
    private static final String TAG = "ReviewActivity";
    private static final String KEY_FOOD = "Food";
    private static final String KEY_DRINK = "Drinks";
    private static final String KEY_ATMOSPHERE = "Atmosphere";
    private static final String KEY_COMMENT = "comment";

    //creating variables
    private String barName;
    private RatingBar foodRate;
    private RatingBar drinksRate;
    private RatingBar atmosphereRate;
    private TextInputEditText comment;
    private Button submit;

    private String url = "http://ndsucsci415.herokuapp.com/api/reviews/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //passing in the name of the bar
        Intent intent = getIntent();
        barName = intent.getExtras().getString("barName");

        //initializing the variables
        foodRate = findViewById(R.id.foodRate);
        drinksRate = findViewById(R.id.drinkRate);
        atmosphereRate = findViewById(R.id.atmosphereRate);
        comment = findViewById(R.id.commentEdit);
    }

    //button to submit the rating
    public void saveRating(View v){
        //getting the num stars for each category from the user
        int food = (int)foodRate.getRating();
        int drink = (int)drinksRate.getRating();
        int atmosphere = (int)atmosphereRate.getRating();
        String commentIn = comment.getText().toString();

        //putting the review info into a json object to send to our database
        JSONObject review = new JSONObject();


        try {
            review.put("Bar", barName);
            review.put("Drinks", drink);
            review.put("Food", food);
            review.put("Atmosphere", atmosphere);
            review.put("Comment", commentIn);
        }
        catch (JSONException e) {
            //TODO lets make sure we dont send this code lmao
            Toast.makeText( ReviewActivity.this , "Error up in jsonbuilding", Toast.LENGTH_SHORT).show();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( url, review, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                //Code for after review is sent


            }
        }
        , new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.e("PostRequest", error.toString());
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        //after review is submitted, going back to that bar's page
        if (barName.equals("The Old Broadway")){
            Intent obIntent = new Intent(getApplicationContext(), OldBroadway.class);
            startActivity(obIntent);
            finish();
        }
        else if(barName.equals("Herd and Horns")){
            Intent obIntent = new Intent(getApplicationContext(), HerdAndHorns.class);
            startActivity(obIntent);
            finish();
        }
        else if(barName.equals("Sports Bar")){
            Intent obIntent = new Intent(getApplicationContext(), SportsBar.class);
            startActivity(obIntent);
            finish();
        }






    }
}
