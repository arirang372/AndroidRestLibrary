package com.john.restlib.builder;


import com.john.restlib.model.BaseRestModel;
import com.john.restlib.model.TypedJsonString;

/**
 * Created by john on 10/29/2015.
 */
public abstract class BaseRequestBuilder
{
    protected TypedJsonString result;

    public TypedJsonString getRequest()
    {
        return result;
    }

    public abstract  TypedJsonString buildPayload(BaseRestModel model, String toApplicationName);
}
