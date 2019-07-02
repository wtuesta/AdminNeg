package com.adminneg.bussines.administrar.negocio.adminneg.apibas;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceJWTBas  {

    private static final String BASE_URL_JWT = "http://192.168.100.3";
  //private static final String BASE_URL_JWT = "http://192.168.100.3:8046";
    private static WebServiceJWTBas instance;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;

    private WebServiceJWTBas(){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_JWT)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized WebServiceJWTBas getInstance(){
        if(instance == null){
            instance = new WebServiceJWTBas();
        }
        return instance;
    }

    public <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
