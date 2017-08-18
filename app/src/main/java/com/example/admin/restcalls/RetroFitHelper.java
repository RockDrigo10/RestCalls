package com.example.admin.restcalls;

import com.example.admin.restcalls.model.WeatherData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public class RetroFitHelper {
    public static final String BASE_URL = "http://samples.openweathermap.org/";
    public static final String PATH = "data/2.5/forecast";;
    public static final String QUERY_ZIP = "94040";
    public static final String QUERY_APPID = "b1b15e88fa797225412429c1c50c122a1";

    public static Retrofit create(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Call<WeatherData> getWeatherCall(){
        Retrofit retrofit =  create();
        WeatherService weatherService = retrofit.create(WeatherService.class);
        return weatherService.getWeatherData(QUERY_ZIP,QUERY_APPID);
    }
    public static Observable<WeatherData> weatherDataObservable(){
        Retrofit retrofit = create();
        WeatherService weatherService =  retrofit.create(WeatherService.class);
        return  weatherService.getWeatherDataObservable(QUERY_ZIP,QUERY_APPID);

    }
    public interface WeatherService{
        @GET(PATH)
        Call<WeatherData> getWeatherData(@Query("zip") String zipcode, @Query("appid") String appId);

        @GET(PATH)
        Observable<WeatherData> getWeatherDataObservable(@Query("zip") String zipcode, @Query("appid") String appId);
    }
}
