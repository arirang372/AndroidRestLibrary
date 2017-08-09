package com.john.restlib.builder;


import com.john.restlib.model.BaseRestModel;
import com.google.gson.GsonBuilder;

/**
 * Created by john on 10/29/2015.
 */
public class RequestResponseRetroTwoBuilder extends BaseRequestRetroTwoBuilder
{
    private static final String APPLICATION_NAME = "thisApplication";
    public static final String DEFAULT_SIGNATURE = "signature";
    public static final String DEFAULT_PASSWORD = "COD5300STE6";
    public RequestResponseRetroTwoBuilder()
    {

    }



    private String toJSON(Object object)
    {
        if(object == null)
            return "";
        return new GsonBuilder().create().toJson(object);
    }

    private String toJSON(BaseRestModel entity)
    {
        if(entity == null)
            return "";

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();

        return builder.create().toJson(entity);
    }


}
