package com.example.user.weatherappadvanced.Adapter;

import com.example.user.weatherappadvanced.API.WeatherAPI;
import com.example.user.weatherappadvanced.Model.WeatherData;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by User on 03/03/2018.
 */

public class WeatherAdapter {
    private static WeatherAdapter instance;
    private WeatherAPI weatherAPI;

    private WeatherAdapter() {
        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(WeatherAPI.BASE_URL)
                .build();

        weatherAPI=retrofit.create(WeatherAPI.class);
    }

    public static WeatherAdapter getInstance() {
        if(instance==null)
        {
            instance=new WeatherAdapter();
        }
        return instance;
    }

    public Observable<WeatherData> getWeather(String city)
    {
        return weatherAPI.getWeather(city,WeatherAPI.API_KEY);
    }
}
