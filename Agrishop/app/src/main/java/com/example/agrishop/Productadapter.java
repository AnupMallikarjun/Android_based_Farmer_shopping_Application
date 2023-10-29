package com.example.agrishop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Productadapter extends RecyclerView.Adapter<Productadapter.ViewHolder>{
    Context context;
    List<productmodel> productmodelList;
    FirebaseFirestore firebaseFirestore;

    public Productadapter(Context context, List<productmodel> productmodelList) {
        this.context = context;
        this.productmodelList = productmodelList;
        this.firebaseFirestore = firebaseFirestore;
    }

    public void setFiltertedList(List<productmodel> filtertedList){
        this.productmodelList = filtertedList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Productadapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(productmodelList.get(position).getUri()).into(holder.imageView);
        holder.name.setText(productmodelList.get(position).getName());
        holder.rate.setText(String.valueOf(productmodelList.get(position).getRate()));
        //holder.rate.setText(product_modelList.get(position).getRate());
        holder.des.setText(productmodelList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details_Activity.class);
                intent.putExtra("detail",productmodelList.get(position));
                intent.putExtra("type",productmodelList.get(position).getType());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

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



        }
    }
}
