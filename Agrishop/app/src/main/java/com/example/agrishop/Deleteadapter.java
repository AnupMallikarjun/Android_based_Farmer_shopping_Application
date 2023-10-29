package com.example.agrishop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

public class Deleteadapter extends RecyclerView.Adapter<Deleteadapter.ViewHolder>{
    Context context;
    List<productmodel> productmodelList;
    FirebaseFirestore firebaseFirestore;
    public Deleteadapter(Context context, List<productmodel> productmodelList) {
        this.context = context;
        this.productmodelList = productmodelList;
        firebaseFirestore=FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Deleteadapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.delete,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(productmodelList.get(position).getUri()).into(holder.imageView);
        holder.name.setText(productmodelList.get(position).getName());
        holder.rate.setText(String.valueOf(productmodelList.get(position).getRate()));
        //holder.rate.setText(product_modelList.get(position).getRate());
        holder.des.setText(productmodelList.get(position).getDescription());



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Product").whereEqualTo("delete","0")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                WriteBatch batch = FirebaseFirestore.getInstance().batch();
                                List<DocumentSnapshot> doc = queryDocumentSnapshots.getDocuments();
                                for (DocumentSnapshot snapshot:doc){
                                    batch.delete(snapshot.getReference());
                                }
                                batch.commit()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(context, "Delete ", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return productmodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,delete;
        TextView name,rate,des;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.p_image);
            name=itemView.findViewById(R.id.pname);
            rate=itemView.findViewById(R.id.prate);
            des=itemView.findViewById(R.id.pdes);
            delete=itemView.findViewById(R.id.deleteproduct);

        }
    }
}
