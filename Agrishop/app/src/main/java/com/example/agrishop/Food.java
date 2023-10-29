package com.example.agrishop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Food extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    ///Product
    RecyclerView productView;
    List<productmodel> productmodelList;
    Productadapter productadapter;
   //private SearchView searchView;
   SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);



        database=FirebaseFirestore.getInstance();
        productView=findViewById(R.id.foodrec);
        productView.setHasFixedSize(true);

        //productView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
        productmodelList = new ArrayList<>();
        Productadapter adapter=new Productadapter(getApplicationContext(), productmodelList);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);

        // at last set adapter to recycler view.
        productView.setLayoutManager(layoutManager);
        productView.setAdapter(adapter);
        //  product_adapter = new Product_Adapter(getApplicationContext(),product_modelList);
        // productView.setAdapter(product_adapter);


        database.collection("Product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult() ){
                                productmodel product_model = document.toObject(productmodel.class);
                                productmodelList.add(product_model);
                                // product_adapter.notifyDataSetChanged();
                                adapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//





    }

    private void filterList(String s) {
        List<productmodel>  filteredList = new ArrayList<>();
        for (productmodel item : productmodelList){
            if (item.getName().toLowerCase().contains(s.toLowerCase())){
            filteredList.add(item);
        }
    }

    }
    
}