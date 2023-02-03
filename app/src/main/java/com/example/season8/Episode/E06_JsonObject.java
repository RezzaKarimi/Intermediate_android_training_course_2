package com.example.season8.Episode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.season8.R;
import com.example.season8.adapter.E06Adapter;
import com.example.season8.app.AsyncHttp;
import com.example.season8.app.Router;
import com.example.season8.app.app;
import com.example.season8.objects.E06Object;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class E06_JsonObject extends AppCompatActivity {

    RecyclerView recyclerView;
    E06Adapter adapter;
    List<E06Object> list = new ArrayList<>();

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e06__json_object);
        setTitle(getClass().getSimpleName());
        init();
    }

    private void init() {
        recyclerView       = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        adapter = new E06Adapter(this , list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(this , android.R.anim.fade_in)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        getData();
    }

    private void getData() {
        swipeRefreshLayout.setRefreshing(true);
        AsyncHttp.get("http://10.0.2.2/Season8/E06JsonObject.php", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                parsJson(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                app.l("****** Error: "+statusCode);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void parsJson(String data){
        try {
            list.clear();

            JSONArray jsonArray = new JSONArray(data);
            int len = jsonArray.length();
            for (int i =0 ; i<len ; i++){
                E06Object object = new E06Object();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                object.setCustomerID(jsonObject.getInt(Router.CustomerID));
                object.setCustomerName(jsonObject.getString(Router.CustomerName));
                object.setContactName(jsonObject.getString(Router.ContactName));
                object.setAddress(jsonObject.getString(Router.Address));
                object.setCity(jsonObject.getString(Router.City));
                object.setPostalCode(jsonObject.getString(Router.PostalCode));
                object.setCountry(jsonObject.getString(Router.Country));

                list.add(object);

            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            app.l("////////"+ e.toString());
        }
    }
}