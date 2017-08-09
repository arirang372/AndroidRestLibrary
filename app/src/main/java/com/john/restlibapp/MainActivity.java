package com.john.restlibapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.john.restlib.RestfulServiceWrapper;
import com.john.restlib.RetrofitTwoServiceWrapper;
import com.john.restlibapp.models.HackerNewsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.john.restlibapp.rest.RetroOneServerEndpoints;
import com.john.restlibapp.rest.RetroTwoServerEndpoints;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity
{
    private static final String BASE_URL_RETRO_ONE = "https://hacker-news.firebaseio.com/";
    private static final String BASE_URL_RETRO_TWO = "https://hacker-news.firebaseio.com";

    private Button btnRetrofitOne;
    private Button btnRetrofitTwo;
    private TextView txtRequestResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnRetrofitOne = (Button) findViewById(R.id.btnRetrofitOne);
        btnRetrofitTwo = (Button) findViewById(R.id.btnRetrofitTwo);
        txtRequestResult = (TextView) findViewById(R.id.txtRequestResult);

        setSupportActionBar(toolbar);

        btnRetrofitOne.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                txtRequestResult.setText("");
                getStoryRetrofitOne();
            }
        });

        btnRetrofitTwo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                txtRequestResult.setText("");
                getStoryRetrofitTwo();
            }
        });
    }

    private void getStoryRetrofitOne()
    {
        final RestfulServiceWrapper<RetroOneServerEndpoints> wrapper = new RestfulServiceWrapper<>();
        RetroOneServerEndpoints endpoints = wrapper.createEndpoint(BASE_URL_RETRO_ONE,
                RetroOneServerEndpoints.class);
        endpoints.getStory(8863,"pretty", new Callback<Response>() {
            @Override
            public void success(Response result, Response response)
            {
                BufferedReader reader;

                try
                {

                    reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                    //convert the json string back to object
                    Gson gson = new GsonBuilder().disableHtmlEscaping()
                                                 .setPrettyPrinting()
                                                 .serializeNulls()
                                                 .create();

                    HackerNewsResponse res = gson.fromJson(reader, HackerNewsResponse.class);
                    String json = gson.toJson(res);
                    txtRequestResult.setText(json);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error)
            {

            }
        });

    }


    private void getStoryRetrofitTwo()
    {

        final RetrofitTwoServiceWrapper<RetroTwoServerEndpoints> wrapper2 = new RetrofitTwoServiceWrapper<>();
        Call<ResponseBody> call = wrapper2.createEndpoint(BASE_URL_RETRO_TWO, RetroTwoServerEndpoints.class)
                                            .getStory(8863,"pretty");

        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response)
            {
                if(response.body() != null)
                {
                    BufferedReader reader;

                    try
                    {

                        reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));

                        //convert the json string back to object
                        Gson gson = new GsonBuilder().disableHtmlEscaping()
                                .setPrettyPrinting()
                                .serializeNulls()
                                .create();

                        HackerNewsResponse res = gson.fromJson(reader, HackerNewsResponse.class);
                        String json = gson.toJson(res);
                        txtRequestResult.setText(json);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.v("onFailure", "called...");
                call.cancel();

            }
        });

    }


}
