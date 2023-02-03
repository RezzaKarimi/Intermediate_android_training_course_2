package com.example.season8.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.season8.Episode.E09_SingleCustomer;
import com.example.season8.R;
import com.example.season8.app.Application;
import com.example.season8.app.app;
import com.example.season8.objects.E09Object;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class E09Adapter extends RecyclerView.Adapter<E09Adapter.ViewHolder> {

    Activity activity;
    List<E09Object> list;

    public E09Adapter(Activity activity , List<E09Object> list){
    this.activity = activity;
    this.list     = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(Application.getContext()).inflate(R.layout.e09_row , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(list.get(position).getCustomerName());
        holder.imageLoader.displayImage("http://ashkaran.ir/learnfiles/season8/" + (list.get(position).getImage()), holder.image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                app.l("///////////onLoadingStarted");
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                app.l("///////////onLoadingFailed");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                app.l("///////////onLoadingComplete");
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                app.l("///////////onLoadingCancelled");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout parent;
        CircleImageView image;
        TextView name;
        ImageLoader imageLoader = ImageLoader.getInstance();
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            image  = itemView.findViewById(R.id.image);
            name   = itemView.findViewById(R.id.name);
            imageLoader.init(Application.config);

            parent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity , E09_SingleCustomer.class);
            intent.putExtra(E09_SingleCustomer.Customer_Key , list.get(getAdapterPosition()));
            activity.startActivity(intent);
        }
    }
}
