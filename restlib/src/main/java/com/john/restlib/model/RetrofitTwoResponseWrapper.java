package com.john.restlib.model;


import com.john.restlib.builder.RequestResponseRetroTwoBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import okhttp3.ResponseBody;


/**
 * Created by john on 12/15/2015.
 */

public class RetrofitTwoResponseWrapper implements IRestResponse
{
    private ResponseBody response;
    private String toApplicationName = "";
    private RequestResponseRetroTwoBuilder retroTwoBuilder;
    private BaseRestModel model;

    public RetrofitTwoResponseWrapper()
    {

    }

    public RetrofitTwoResponseWrapper(ResponseBody response)
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
            reader = new BufferedReader(new InputStreamReader(this.response.byteStream()));

            //convert the json string back to object
            Gson gson = new GsonBuilder().disableHtmlEscaping()
                                         .setPrettyPrinting()
                                         .serializeNulls()
                                         .create();

            responseString = gson.fromJson(reader, String.class);

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
    public BaseRestModel readRetroTwo()
    {
        String responseString;
        BufferedReader reader = null;
        BaseRestModel res = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(this.response.byteStream()));

            //convert the json string back to object
            Gson gson = new GsonBuilder().disableHtmlEscaping()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create();

            responseString = gson.fromJson(reader, String.class);

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

}
