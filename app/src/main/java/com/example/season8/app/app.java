package com.example.season8.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.InetAddresses;
import android.util.Log;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class app {
    public static class main{
        public static final String Tag = "Season8";
    }

    public static void l(String message){
        Log.e(main.Tag , message);
    }

    public static void t(String message){
        Toast.makeText(Application.getContext() , message , Toast.LENGTH_SHORT).show();
    }

    public static Boolean checkConnectivity(){

        ConnectivityManager connectivityManager = (ConnectivityManager) Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);


        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static Boolean checkData(){

        try {
            InetAddress inetAddress = InetAddress.getByName("www.google.com");

            return !inetAddress.equals("");

        } catch (UnknownHostException e) {
//            app.l(e.toString());
            return false;
        }
    }

}
