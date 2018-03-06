package com.example.user.weatherappadvanced.Application;

import android.app.Application;

import com.example.user.weatherappadvanced.DI.AppComponent;
import com.example.user.weatherappadvanced.DI.AppModule;
import com.example.user.weatherappadvanced.DI.DaggerAppComponent;




/**
 * Created by User on 05/03/2018.
 */

public class WeatherApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }
}
