package com.example.season8.Episode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.season8.R;
import com.example.season8.app.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class E01_HttpsConnection extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView search_butt;
    AppCompatEditText  search_box ;
    TextView           result     ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e01__https_connection);
        setTitle(getClass().getSimpleName());
        init();
    }

    private void init() {
        search_butt = findViewById(R.id.search_butt);
        search_box  = findViewById(R.id.search_box) ;
        result      = findViewById(R.id.result)     ;

        search_butt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        app.l("ready");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    httpConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void httpConnection() throws IOException {
        URL url = new URL(search_box.getText().toString());
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        urlConnection.setReadTimeout(3000);

        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line+"\n");
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                result.setText(stringBuffer.toString());
            }
        });
        app.l(stringBuffer.toString());
    }

//   private class AsyncHttp extends AsyncTask<String , String , String>{
//
//       @Override
//       protected String doInBackground(String... params) {
//           return null;
//       }                                                                                  BADAN CHECK BESHE SHAYAD BE DARD KHORD
//
//       @Override
//       protected void onPostExecute(String s) {
//           super.onPostExecute(s);
//       }
//   }
}