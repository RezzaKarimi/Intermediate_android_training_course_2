package com.example.season8.Episode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.season8.R;
import com.example.season8.adapter.E09Adapter;
import com.example.season8.app.AsyncHttp;
import com.example.season8.objects.E09Object;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class E09_ImageLoader extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    E09Adapter adapter;
    List<E09Object> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e09__image_loader);
        setTitle(getClass().getSimpleName());
        init();
    }

    private void init() {

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView       = findViewById(R.id.recyclerView);
        adapter            = new E09Adapter(this , list);

        recyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(this , android.R.anim.fade_in)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        getData();

    }

    private void getData(){
        swipeRefreshLayout.setRefreshing(true);
        AsyncHttp.get("http://10.0.2.2/Season8/E09ImageLoader.php", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                list.clear();
                Gson gson = new Gson();
                E09Object[] object = gson.fromJson(new String(responseBody) , E09Object[].class);
                list.addAll(Arrays.asList(object));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}