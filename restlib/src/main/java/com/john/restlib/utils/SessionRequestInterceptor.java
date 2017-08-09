package com.john.restlib.utils;

import retrofit.RequestInterceptor;

/**
 * Created by john on 10/28/2015.
 */
public class SessionRequestInterceptor implements RequestInterceptor
{
    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Content-Type", "application/json");
    }
}
