package com.example.thebarapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class OldBroadway extends AppCompatActivity {
    private TextView textData;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference reviewRef = db.collection("bars/1/reviews");
    private static final String TAG = "OldBroadway";
    private RatingBar foodRate;
    private RatingBar drinksRate;
    private RatingBar atmosphereRate;
    private RatingBar overallRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_broadway);
        textData = (TextView)findViewById(R.id.commentTxt);
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
        db.collection("bars").document("1").collection("review")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String comment = "";
                        String data = "";
                        int food=0;
                        int drink=0;
                        int atm=0;
                        int overall=0;
                        int x=0;
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                comment = document.getString("comment");
                                data += "Comment: " + comment + "\n\n";
                                food += document.getLong("food").intValue();
                                drink+= document.getLong("drinks").intValue();
                                atm += document.getLong("atmosphere").intValue();
                                x++;
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                        textData.setText(data);
                        foodRate.setRating(food/x);
                        drinksRate.setRating(drink/x);
                        atmosphereRate.setRating(atm/x);
                        overallRate.setRating((food + drink + atm)/x/3);
                    }
                });




    }






}
