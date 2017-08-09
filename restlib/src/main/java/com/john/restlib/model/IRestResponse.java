package com.john.restlib.model;


/**
 * Created by john on 7/1/2016.
 */
public interface IRestResponse {
    IRestResponse setModel(BaseRestModel model);
    IRestResponse setTOApplication(String toApplication);
    BaseRestModel read();
    BaseRestModel readRetroTwo();
}
