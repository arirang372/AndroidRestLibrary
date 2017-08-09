package com.john.restlib.model;

import retrofit.mime.TypedString;

/**
 * Created by john on 10/28/2015.
 */

//this is the class that enables me to send raw JSON String without FormEncodedUrl via retrofit.
public class TypedJsonString extends TypedString
{
    public TypedJsonString(String body) {
        super(body);
    }

    @Override
    public String mimeType() {
        return "application/json";
    }
}
