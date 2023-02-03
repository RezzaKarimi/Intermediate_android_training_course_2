package com.example.season8.Episode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.season8.R;
import com.example.season8.objects.E06Object;

public class E06_CustomersData extends AppCompatActivity {

    public static final String Customer_Key = "CUSTOMERS";
    E06Object object;
    TextView CustomerID , CustomerName , ContactName , Address , City , PostalCode , Country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e06__customers_data);
        object = (E06Object) getIntent().getSerializableExtra(Customer_Key);
        setTitle(object.getCustomerName());

        init();

    }

    private void init() {

        setText((R.id.CustomerID) , object.getCustomerID()+"");
        setText((R.id.CustomerName) , object.getCustomerName());
        setText((R.id.ContactName) , object.getContactName());
        setText((R.id.Address) , object.getAddress());
        setText((R.id.City) , object.getCity());
        setText((R.id.PostalCode) , object.getPostalCode());
        setText((R.id.Country) , object.getCountry());
    }


    private void setText(int resId , String message){
        TextView textView = findViewById(resId);
        textView.setText(message);
    }
}