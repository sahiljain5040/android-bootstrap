package com.demo.androidbootstrap.di.modules;

import android.app.Application;
import android.util.Log;

import com.demo.androidbootstrap.calladapter.RxErrorHandlingCallAdapterFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sahil on 10/14/17.
 */
@Module
public class NetworkModule {

    private static final String TAG = NetworkModule.class.getSimpleName();

    private String mBaseUrl;

    public NetworkModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        Log.d(TAG, "provideOkHttpClientNectors called");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().cache(cache);
        builder.networkInterceptors().add(interceptor);

        OkHttpClient client = builder.build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();

        return retrofit;
    }
}
