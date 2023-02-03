package com.example.season8.app;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.season8.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class Application extends android.app.Application {
    private static Context context;
    public static ImageLoaderConfiguration config;
    public static DisplayImageOptions options;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        config = new ImageLoaderConfiguration.Builder(context)
                .denyCacheImageMultipleSizesInMemory()
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();




        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.image) // resource or drawable
                .showImageForEmptyUri(R.drawable.no_image) // resource or drawable
                .showImageOnFail(R.drawable.no_image) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
		        .displayer(new SimpleBitmapDisplayer()) // default

                .build();


    }

    public static Context getContext(){
        return context;
    }
}
