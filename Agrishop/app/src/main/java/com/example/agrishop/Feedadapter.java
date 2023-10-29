package com.example.agrishop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Feedadapter extends RecyclerView.Adapter<Feedadapter.ViewHolder>{

    Context context;
    List<Feedmodel> feedmodelList;

    public Feedadapter(Context context, List<Feedmodel> feedmodelList) {
        this.context = context;
        this.feedmodelList = feedmodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Feedadapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(feedmodelList.get(position).getName());
        holder.eamil.setText(feedmodelList.get(position).getEmail());
        holder.feedback.setText(feedmodelList.get(position).getFeedback());
    }

    @Override
    public int getItemCount() {
        return feedmodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
TextView name,eamil,feedback;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.fedname);
            eamil=itemView.findViewById(R.id.fedemail);
            feedback=itemView.findViewById(R.id.fedfedback);
        }
    }
}
