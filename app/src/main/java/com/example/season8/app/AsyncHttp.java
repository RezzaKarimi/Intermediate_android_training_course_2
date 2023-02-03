package com.example.season8.app;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AsyncHttp {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String uri , RequestParams params , AsyncHttpResponseHandler responseHandler){
        client.get(uri , params , responseHandler);
    }

    public static void post(String uri , RequestParams params , AsyncHttpResponseHandler responseHandler){
        client.post(uri , params , responseHandler);
    }
}
