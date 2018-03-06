package com.example.user.weatherappadvanced.API;

import com.example.user.weatherappadvanced.Model.WeatherData;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by User on 03/03/2018.
 */

public interface WeatherAPI {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    String API_KEY = "22b004045adff0ce11c220d27794bbde";

    @GET("weather?")
    Observable<WeatherData> getWeather(@Query("q") String city,
                                       @Query("appid") String appid);


}
