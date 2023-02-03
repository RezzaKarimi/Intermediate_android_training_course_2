package com.example.season8.Episode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.season8.R;
import com.example.season8.app.app;

public class E02_ConnectionStatus extends AppCompatActivity {

    AppCompatButton check ;
    TextView        status;
    Boolean         data  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e02__connection_status);
        setTitle(getClass().getSimpleName());
        init();
    }

    private void init() {
        check  = findViewById(R.id.check) ;
        status = findViewById(R.id.status);

        check.setOnClickListener(v -> {
            app.l("check ready");

            status.setText(app.checkConnectivity()?"Connected":"Not Connected");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    data = app.checkData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            status.append(data?" - Data Available":" - No Data");
                        }
                    });
                }
            }).start();
        });
    }
}