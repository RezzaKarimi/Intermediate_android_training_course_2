package com.example.season8.Episode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.season8.R;

public class E03_NetworkChangeReceiver extends AppCompatActivity {

    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e03__network_change_receiver);

        setTitle(getClass().getSimpleName());
        status = findViewById(R.id.status);





    }
}