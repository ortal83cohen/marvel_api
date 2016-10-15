package com.marvel.api.ortal.client;

import com.marvel.api.ortal.utils.Security;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.util.ArrayMap;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Map;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public class Api {

    public static final String PATH_CHARACTERS = "/v1/public/characters";

    private OkHttpClient mHttpClient;

    private ApiConfig mConfig;

    private Interceptor mRequestInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String ts = getTs();
            HttpUrl url = request.httpUrl().newBuilder()
                    .addQueryParameter("apikey", mConfig.getPublicKey())
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("hash", Security.md5(ts.concat(mConfig.getPrivateKey()).concat(mConfig.getPublicKey())))
                    .build();

            return chain.proceed(request.newBuilder().url(url).build());
        }
    };

    public Api(ApiConfig config, OkHttpClient httpClient) {
        mConfig = config;
        mHttpClient = httpClient == null ? new OkHttpClient() : httpClient;
        mHttpClient.interceptors().add(0, mRequestInterceptor);

    }

    private String getTs() {
        Long tsLong = System.currentTimeMillis() / 1000;
        return tsLong.toString();
    }

    private Service create() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(mHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mConfig.getEndpoint());

        Retrofit restAdapter = builder.build();

        return restAdapter.create(Service.class);
    }


    public Call<MarvelResponseBody> search(String name) throws InvalidParameterException {

        Service service = create();

        ArrayMap<String, String> query = new ArrayMap<>();

        query.put("name", name);

        return service.search(query);
    }


    public interface Service {

        @GET(PATH_CHARACTERS)
        Call<MarvelResponseBody> search(@QueryMap Map<String, String> query);

    }

}
