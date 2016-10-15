package com.marvel.api.ortal.client;


import com.marvel.api.ortal.utils.PropertiesUtil;

import android.content.Context;

import java.io.IOException;

public class ApiConfig {

    public ApiConfig(Context context) throws IOException {
        mContext = context;
        mPublicKey = PropertiesUtil.getProperty("PublicKey", mContext);

        mPrivateKey = PropertiesUtil.getProperty("PrivateKey", mContext);
    }

    private String mPublicKey;

    private String mPrivateKey;

    private Context mContext;

    public static final String API_ENDPOINT_DEFAULT = "http://gateway.marvel.com:80/v1/public/characters";


    private String mEndpoint = API_ENDPOINT_DEFAULT;

    private HttpLoggingInterceptor.Logger logger;

    private boolean mDebug;

    public ApiConfig() throws IOException {
    }

    public String getPublicKey() {
        return mPublicKey;
    }

    public String getPrivateKey() {
        return mPrivateKey;
    }

    public String getEndpoint() {
        return mEndpoint;
    }

    public boolean isDebug() {
        return mDebug;
    }

    public void setDebug(boolean debug) {
        mDebug = debug;
    }

    public HttpLoggingInterceptor.Logger getLogger() {
        return logger;
    }

    public void setLogger(HttpLoggingInterceptor.Logger logger) {
        this.logger = logger;
    }
}
