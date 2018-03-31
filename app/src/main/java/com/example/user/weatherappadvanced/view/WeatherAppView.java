package com.example.user.weatherappadvanced.view;

import android.app.Activity;
import android.os.IBinder;
import android.view.View;

import com.example.user.weatherappadvanced.model.WeatherData;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by User on 03/03/2018.
 */

public interface WeatherAppView extends MvpView {

    void showLoad();
    void hideLoad();

    void onWeatherObtained(WeatherData weatherData);
    //void ClearWeather();
    void hideKeyboard(Activity activity, IBinder windowToken);
    //void displayWeather(WeatherData weatherData);
    void showWeather(View view);
    //void logQueriedData(WeatherData weatherData);
    void switchMetric(View view);
    void showErr(String message);
}
