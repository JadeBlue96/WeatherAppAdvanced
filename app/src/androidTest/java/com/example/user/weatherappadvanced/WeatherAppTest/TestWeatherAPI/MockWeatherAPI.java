package com.example.user.weatherappadvanced.WeatherAppTest.TestWeatherAPI;

import com.example.user.weatherappadvanced.API.WeatherAPI;
import com.example.user.weatherappadvanced.Model.WeatherData;
import com.example.user.weatherappadvanced.WeatherAppTest.TestModel.TestWeatherData;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by User on 05/03/2018.
 */

public class MockWeatherAPI implements WeatherAPI {
    @Override
    public Observable<WeatherData> getWeather(String city, String appid) {
        WeatherData weatherData = new Gson().fromJson(TestWeatherData.LONDON_WEATHER_DATA_JSON, WeatherData.class);
        return Observable.just(weatherData).delay(1, TimeUnit.SECONDS);
    }
}
