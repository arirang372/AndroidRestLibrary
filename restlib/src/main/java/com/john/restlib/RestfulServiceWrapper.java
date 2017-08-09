package com.john.restlib;

import android.content.Context;

import com.john.restlib.utils.SessionRequestInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by john on 3/11/2016.
 */

public class RestfulServiceWrapper<T>
{
    private Context context;
    private static final int HTTP_TIMEOUT = 20000;

    public RestfulServiceWrapper()
    {

    }

    public T createEndpoint(String url, Class<T> className)
    {

        Gson gson = new GsonBuilder().create();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setWriteTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);

        RestAdapter adapter = new RestAdapter.Builder()
                                             .setLogLevel(RestAdapter.LogLevel.FULL)
                                             .setEndpoint(url)
                                             .setConverter(new GsonConverter(gson))
                                             .setClient(new OkClient(okHttpClient))
                                             .setRequestInterceptor(new SessionRequestInterceptor())
                                             .build();

        return adapter.create(className);
    }



}
