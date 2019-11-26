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
    private static final String TAG = "ReviewActivity";
    private static final String KEY_FOOD = "Food";
    private static final String KEY_DRINK = "Drinks";
    private static final String KEY_ATMOSPHERE = "Atmosphere";
    private static final String KEY_COMMENT = "comment";

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

        Intent intent = getIntent();
        barName = intent.getExtras().getString("barName");

        foodRate = findViewById(R.id.foodRate);
        drinksRate = findViewById(R.id.drinkRate);
        atmosphereRate = findViewById(R.id.atmosphereRate);
        comment = findViewById(R.id.commentEdit);
    }

    public void saveRating(View v){
        int food = (int)foodRate.getRating();
        int drink = (int)drinksRate.getRating();
        int atmosphere = (int)atmosphereRate.getRating();
        String commentIn = comment.getText().toString();


        JSONObject review = new JSONObject();

        Toast.makeText( ReviewActivity.this , "Made it this far", Toast.LENGTH_SHORT).show();
        try {
            review.put("Bar", barName);
            review.put(KEY_DRINK, drink);
            review.put(KEY_FOOD, food);
            review.put(KEY_ATMOSPHERE, atmosphere);
            //review.put(KEY_COMMENT, commentIn);
        }
        catch (JSONException e) {
            Toast.makeText( ReviewActivity.this , "fucked up in jsonbuilding", Toast.LENGTH_SHORT).show();
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

    }
}
