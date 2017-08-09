package com.john.restlibapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by john on 3/15/2016.
 */
public class AuthenticationRequest {
    @SerializedName("SessionToken")
    @Expose
    private String SessionToken = "";
    @SerializedName("UserID")
    @Expose
    private int UserID;
    @SerializedName("Provider")
    @Expose
    private String Provider;
    @SerializedName("issuer")
    @Expose
    private String issuer = "";



    public AuthenticationRequest()
    {

    }



    /**
     *
     * @return
     * The ProviderToken
     */
    public String getSessionToken() {
        return SessionToken;
    }

    /**
     *
     * @param ProviderToken
     * The ProviderToken
     */
    public void setSessionToken(String ProviderToken) {
        this.SessionToken = ProviderToken;
    }

    /**
     *
     * @return
     * The UserID
     */
    public int getUserID() {
        return UserID;
    }

    /**
     *
     * @param UserID
     * The UserID
     */
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    /**
     *
     * @return
     * The Provider
     */
    public String getProvider() {
        return Provider;
    }

    /**
     *
     * @param Provider
     * The Provider
     */
    public void setProvider(String Provider) {
        this.Provider = Provider;
    }

    public String getIssuer()
    {
        return issuer;
    }

    /**
     *
     * @param issuer
     * The issuer
     */
    public void setIssuer(String issuer)
    {
        this.issuer = issuer;
    }


}
