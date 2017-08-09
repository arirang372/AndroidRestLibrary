package com.john.restlibapp.rest;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by john on 3/15/2016.
 */
public interface RetroOneServerEndpoints
{
    @GET("/v0/item/{id}.json")
    void getStory(@Path("id") int id, @Query("print") String print, Callback<Response> callback );

}
