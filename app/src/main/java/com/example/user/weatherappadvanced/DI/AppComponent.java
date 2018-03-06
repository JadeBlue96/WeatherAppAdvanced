package com.example.user.weatherappadvanced.DI;

import android.content.Context;

import com.example.user.weatherappadvanced.API.WeatherAPI;
import com.example.user.weatherappadvanced.Activity.MainActivity;

import dagger.Component;

/**
 * Created by User on 05/03/2018.
 */
@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MainActivity activity);

    @AppScope Context appContext();

    WeatherAPI weatherAPI();
}