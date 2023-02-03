package com.example.season8.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.season8.Episode.E06_CustomersData;
import com.example.season8.R;
import com.example.season8.app.Application;
import com.example.season8.app.app;
import com.example.season8.objects.E06Object;

import java.util.ArrayList;
import java.util.List;

public class E06Adapter extends RecyclerView.Adapter<E06Adapter.MyViewHolder> {
    Activity activity;
    List<E06Object> list;
    public E06Adapter(Activity activity, List<E06Object> list){
        this.activity = activity;
        this.list     = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(Application.getContext()).inflate(R.layout.e06_row , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(list.get(position).getCustomerName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        RelativeLayout row;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.row);
            name   = itemView.findViewById(R.id.name);

            row.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(activity , E06_CustomersData.class);
            intent.putExtra(E06_CustomersData.Customer_Key , list.get(getAdapterPosition()));
             activity.startActivity(intent);
        }
    }
}
