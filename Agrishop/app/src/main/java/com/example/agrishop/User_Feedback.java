package com.example.agrishop;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class User_Feedback extends AppCompatActivity {
    EditText name,email,feedback;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        name=findViewById(R.id.ffname);
        email=findViewById(R.id.fgemail);
        feedback=findViewById(R.id.fhfeedback);
        button=findViewById(R.id.fjsubmit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

String  na = name.getText().toString();
String em = email.getText().toString();
String eff = feedback.getText().toString();

                // Create a new user with a first and last name
                HashMap<String, Object> userdataMap = new HashMap<>();
                userdataMap.put("name", na);
                userdataMap.put("email", em);
                userdataMap.put("feedback",eff);

// Add a new document with a generated ID
                db.collection("Feedback")
                        .add(userdataMap)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                Toast.makeText(User_Feedback.this, " Your feedback has been Submit successfully ", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(User_Feedback.this, User_Feedback.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(User_Feedback.this, "Network Error ! Please try again ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });


            }
        });


    }
}