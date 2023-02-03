package com.example.season8.Episode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.season8.R;
import com.example.season8.adapter.E05Adapter;
import com.example.season8.app.Application;
import com.example.season8.app.AsyncHttp;
import com.example.season8.app.app;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class E05_JsonArray extends AppCompatActivity {

    RecyclerView recyclerView;
    E05Adapter adapter;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e05__json_array);
        setTitle(getClass().getSimpleName());
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter      = new E05Adapter(this , list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(Application.getContext() , android.R.anim.fade_in)));
        recyclerView.setLayoutManager(new LinearLayoutManager(Application.getContext()));


        readData();
    }


    private void readData(){
        AsyncHttp.get("http://10.0.2.2/Season8/E05.php", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    parsJsonArray(new String(responseBody));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                app.l(" /////////"+statusCode);
            }
        });
    }

    private void parsJsonArray(String message) throws JSONException {
        list.clear();
        JSONArray jsonArray = new JSONArray(message);
        int len = jsonArray.length();

        for (int i=0 ; i<len ; i++){

            list.add(jsonArray.getString(i));
            adapter.notifyDataSetChanged();

        }

    }
}