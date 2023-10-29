package com.example.agrishop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Mycartadapter extends RecyclerView.Adapter<Mycartadapter.ViewHolder>{
    Context context;
    List<Mycartmodel> mycartmodelList;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    int totalPrice =0;

    public Mycartadapter(Context context, List<Mycartmodel> mycartmodelList) {
        this.context = context;
        this.mycartmodelList = mycartmodelList;
        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycartitem,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mycartmodelList.get(position).getName());
        // holder.price.setText(String.valueOf(myCart_modelList.get(position).getPrice()));
        holder.price.setText(String.valueOf(mycartmodelList.get(position).getPrice()+"₹"));
        holder.quntity.setText(String.valueOf(mycartmodelList.get(position).getQuantity()));
        totalPrice = totalPrice+mycartmodelList.get(position).getPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        holder.deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("AddtoCard").document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser")
                        .document(mycartmodelList.get(position).getDocumentid())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    mycartmodelList.remove(mycartmodelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Delete", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mycartmodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,quntity;
        ImageView deleteitem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.productprice);
            deleteitem=itemView.findViewById(R.id.delete);
            quntity=itemView.findViewById(R.id.totalq);
        }
    }
}
