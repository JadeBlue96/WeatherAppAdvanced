package com.example.user.weatherappadvanced.view;

import android.view.View;

import com.example.user.weatherappadvanced.model.WeatherData;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface FormatWeatherView extends MvpView {
    void ClearWeather();
    void displayWeather(WeatherData weatherData);
    void logQueriedData(WeatherData weatherData);
}
