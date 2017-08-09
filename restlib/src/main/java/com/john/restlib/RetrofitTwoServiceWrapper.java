package com.john.restlib;

import com.john.restlib.builder.RequestResponseRetroTwoBuilder;
import com.john.restlib.model.BaseRestModel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by john on 7/8/2016.
 */
public class RetrofitTwoServiceWrapper<T>
{
    private RequestResponseRetroTwoBuilder builder;
    private String requestString;
    private static final int HTTP_TIMEOUT = 60000;

    public RetrofitTwoServiceWrapper()
    {

    }


    public T createEndpoint(String url, Class<T> className)
    {

        Retrofit.Builder builder = new Retrofit.Builder()
                                               .addConverterFactory(GsonConverterFactory.create())
                                               .baseUrl(url);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder()
                                                              .addInterceptor(interceptor)
                                                              .addInterceptor(new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request();
                Request newReq = request.newBuilder()
                                        .addHeader("Content-Type", "application/json" )
                                        .build();
                return chain.proceed(newReq);
            }
        }).readTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
          .writeTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
          .connectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
          .build();

        builder.client(client);

        return builder.build().create(className);
    }

    public RequestBody getRequest()
    {
        return RequestBody.create(MediaType.parse("text/plain"),  requestString);
    }

    public RequestResponseRetroTwoBuilder getBuilder()
    {
        return builder;
    }
}
