package com.john.restlibapp.rest;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;

/**
 * Created by johns on 8/9/2017.
 */

public interface RetroTwoServerEndpoints
{
    @GET("v0/item/{id}.json")
    Call<ResponseBody> getStory(@Path("id") int id, @Query("print") String print);

}
