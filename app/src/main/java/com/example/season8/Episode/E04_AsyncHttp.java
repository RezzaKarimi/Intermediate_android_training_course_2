package com.example.season8.Episode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.season8.R;
import com.example.season8.app.AsyncHttp;
import com.example.season8.app.app;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class E04_AsyncHttp extends AppCompatActivity implements View.OnClickListener {

    EditText searchBox;
    AppCompatImageView go;
    TextView result;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e04__async_http);
        setTitle(getClass().getSimpleName());
        init();
    }

    private void init() {
        searchBox   = findViewById(R.id.search_box);
        go          = findViewById(R.id.go);
        result      = findViewById(R.id.result);
        progressBar = findViewById(R.id.progress_bar);

        go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    //simple();
        recommended();
    }

    private void simple(){
        progressBar.setVisibility(View.VISIBLE);
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(searchBox.getText().toString(), new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                app.l("onStart");
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                app.l("onSuccess");
                result.setText(new String(responseBody));
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);
                app.l("onFinish");
                super.onFinish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                app.l("onFailure");
            }

            @Override
            public void onRetry(int retryNo) {
                app.l("onRetry");
                super.onRetry(retryNo);
            }

        });
    }

    private void recommended(){

        progressBar.setVisibility(View.VISIBLE);
        AsyncHttp.get(searchBox.getText().toString(), null, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                result.setText(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                result.setText(getString(R.string.failed) +"-" + statusCode);
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);
                super.onFinish();
            }



            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }

        });
    }
}