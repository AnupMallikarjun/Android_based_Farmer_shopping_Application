package com.example.agrishop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Feedback extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    RecyclerView productView;
    List<Feedmodel> feedmodelList;
    Feedadapter feedadapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        database =FirebaseFirestore.getInstance();

        recyclerView=findViewById(R.id.feedbackrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ;

        feedmodelList = new ArrayList<>();
        feedadapter = new Feedadapter(getApplicationContext(),feedmodelList);
        recyclerView.setAdapter(feedadapter);


        //if (type != null && type.equalsIgnoreCase("Email")) {
        database.collection("Feedback")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Feedmodel feedmodel = document.toObject(Feedmodel.class);
                                feedmodelList.add(feedmodel);
                                // product_adapter.notifyDataSetChanged();
                                feedadapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                });



    }
}