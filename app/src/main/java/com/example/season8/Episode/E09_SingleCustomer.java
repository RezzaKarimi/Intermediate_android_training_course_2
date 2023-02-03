package com.example.season8.Episode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.season8.R;
import com.example.season8.app.Application;
import com.example.season8.app.app;
import com.example.season8.objects.E09Object;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class E09_SingleCustomer extends AppCompatActivity {

    E09Object customer;
    ImageView image;
    Toolbar toolbar;

    public static final String Customer_Key = "CUSTOMER_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e09__single_customer);
        image   = findViewById(R.id.image);
        toolbar = findViewById(R.id.toolBar);




        customer = (E09Object) getIntent().getSerializableExtra(Customer_Key);
        setTitle(customer.getCustomerName());

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(customer.getCustomerName());

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(Application.config);

        setText(R.id.CustomerID , customer.getCustomerID()+"");
        setText(R.id.ContactName , customer.getContactName());
        setText(R.id.Address , customer.getAddress());
        setText(R.id.City , customer.getCity());
        setText(R.id.PostalCode , customer.getPostalCode());
        setText(R.id.Country , customer.getCountry());


        imageLoader.displayImage("http://ashkaran.ir/learnfiles/season8/" + customer.getImage() , image, new ImageLoadingListener() {
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

    private void setText(int id , String message){
        TextView txt = findViewById(id);
        txt.setText(message);
    }
}