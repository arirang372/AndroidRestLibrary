# AndroidRestLibrary

This library makes user call the webservice easy. 
Here are some examples below.

```java

This is an example to call webservice (GET Request) through Retrofit 1

public interface RetroOneServerEndpoints
{
    @GET("/v0/item/{id}.json")
    void getStory(@Path("id") int id, @Query("print") String print, Callback<Response> callback );

}

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
					/**
					*  This response can receive any PoJos..
					*/
					
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


This is an example to call webservice (GET Request) through Retrofit 2

public interface RetroTwoServerEndpoints
{
    @GET("v0/item/{id}.json")
    Call<ResponseBody> getStory(@Path("id") int id, @Query("print") String print);

}


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

```
You can also call PUT, OPTIONS, DELETE, and so on...
