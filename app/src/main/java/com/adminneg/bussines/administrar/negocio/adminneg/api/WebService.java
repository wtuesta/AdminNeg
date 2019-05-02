package com.adminneg.bussines.administrar.negocio.adminneg.api;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LeveauT on 1/12/18.
 */

public class WebService {

    private static final String BASE_URL = "http://192.168.100.86/";
    private static WebService instance;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;

    private WebService(){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized WebService getInstance(){
        if(instance == null){
            instance = new WebService();
        }
        return instance;
    }

    public WebServiceApi createService(){
        return retrofit.create(WebServiceApi.class);
    }

    public <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}

