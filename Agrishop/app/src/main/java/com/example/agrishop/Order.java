package com.example.agrishop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Order extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    List<OrderModel> orderModelList;
    FirebaseFirestore db;
    FirebaseAuth auth;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        db =FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.myorderrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        auth=FirebaseAuth.getInstance();
        orderModelList = new ArrayList<>();
        orderAdapter = new OrderAdapter(getApplicationContext(),orderModelList);
        recyclerView.setAdapter(orderAdapter);

        db.collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        String documentid = documentSnapshot.getId();
                        OrderModel myOrderModel = documentSnapshot.toObject(OrderModel.class);
                        //myOrderModel.setDocumentid(documentid);
                        orderModelList.add(myOrderModel);
                        orderAdapter.notifyDataSetChanged();
                    }
                }
            }
        });



    }
}