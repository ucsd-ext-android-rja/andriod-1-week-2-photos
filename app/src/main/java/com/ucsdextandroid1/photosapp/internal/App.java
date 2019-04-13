package com.ucsdextandroid1.photosapp.internal;

import android.app.Application;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by rjaylward on 4/13/19
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso picasso;
        OkHttpClient.Builder okHttpClient;

        okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient.connectTimeout(10, TimeUnit.SECONDS);

        picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(okHttpClient.build()))
                .build();

        Picasso.setSingletonInstance(picasso);
    }
}
