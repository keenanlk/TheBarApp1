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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReviewActivity extends AppCompatActivity {
    private static final String TAG = "ReviewActivity";
    private static final String KEY_FOOD = "food";
    private static final String KEY_DRINK = "drinks";
    private static final String KEY_ATMOSPHERE = "atmosphere";
    private static final String KEY_COMMENT = "comment";

    private int barID;
    private RatingBar foodRate;
    private RatingBar drinksRate;
    private RatingBar atmosphereRate;
    private TextInputEditText comment;
    private Button submit;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Intent intent = getIntent();
        barID = intent.getExtras().getInt("barID");

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

        Map<String, Object> review = new HashMap<>();
        review.put(KEY_ATMOSPHERE, atmosphere);
        review.put(KEY_COMMENT, commentIn);
        review.put(KEY_DRINK, drink);

        review.put(KEY_FOOD, food);

        db.collection("bars").document("1").collection("review").document().set(review)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ReviewActivity.this, "Succesful", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ReviewActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}
