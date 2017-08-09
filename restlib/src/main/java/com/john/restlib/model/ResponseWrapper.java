package com.john.restlib.model;


import android.text.TextUtils;

import com.john.restlib.builder.RequestResponseRetroTwoBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.client.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by john on 12/15/2015.
 */

public class ResponseWrapper implements IRestResponse
{

    private Response response;
    private String toApplicationName = "";
    private BaseRestModel model;

    public ResponseWrapper()
    {

    }

    public ResponseWrapper(Response response)
    {
        this.response = response;
    }

    public IRestResponse setModel(BaseRestModel model)
    {
        this.model = model;
        return this;
    }

    @Override
    public IRestResponse setTOApplication(String toApplication)
    {
        toApplicationName = toApplication;
        return this;
    }



    @Override
    public BaseRestModel read()
    {
        String responseString;
        BufferedReader reader = null;
        BaseRestModel res = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(this.response.getBody().in()));

            //convert the json string back to object
            Gson gson = new GsonBuilder().disableHtmlEscaping()
                                         .setPrettyPrinting()
                                         .serializeNulls()
                                         .create();

            responseString = gson.fromJson(reader, String.class);

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return res;
    }

    @Override
    public BaseRestModel readRetroTwo() {
        return null;
    }

}
