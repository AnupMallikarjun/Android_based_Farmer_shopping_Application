package com.example.agrishop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    List<OrderModel> orderModelList;

    public OrderAdapter(Context context, List<OrderModel> orderModelList) {
        this.context = context;
        this.orderModelList = orderModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(orderModelList.get(position).getName());
      // holder.price.setText(String.valueOf(orderModelList.get(position).getPrice()));
       holder.price.setText(String.valueOf(orderModelList.get(position).getPrice()+"₹"));
       holder.quntity.setText(String.valueOf(orderModelList.get(position).getQuantity()));
      holder.time.setText(orderModelList.get(position).getTime());
       holder.date.setText(orderModelList.get(position).getDate());
       // holder.address.setText(orderModelList.get(position).getName());
        holder.city.setText(orderModelList.get(position).getCity());
        holder.landmark.setText(orderModelList.get(position).getLandmark());
        holder.loclity.setText(orderModelList.get(position).getLocality());
        holder.mobilenumber.setText(orderModelList.get(position).getMobilenumber());
        holder.pincode.setText(orderModelList.get(position).getPincode());
        holder.alternative.setText(orderModelList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,date,price,quntity,time;
        TextView address,city,landmark,loclity,mobilenumber,pincode,alternative;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.order_name);
            date=itemView.findViewById(R.id.order_date);
            price=itemView.findViewById(R.id.order_price);
            quntity=itemView.findViewById(R.id.order_quantity);
            time=itemView.findViewById(R.id.order_time);
            address = itemView.findViewById(R.id.address);
            city=itemView.findViewById(R.id.city);
            landmark=itemView.findViewById(R.id.landmark);
            loclity=itemView.findViewById(R.id.locality);
            mobilenumber=itemView.findViewById(R.id.mobile);
            pincode=itemView.findViewById(R.id.pincode);
            alternative=itemView.findViewById(R.id.alternative);

        }
    }
}
