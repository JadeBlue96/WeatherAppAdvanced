package com.example.user.weatherappadvanced.WeatherAppTest.TestDI;

import android.content.Context;

import com.example.user.weatherappadvanced.API.WeatherAPI;
import com.example.user.weatherappadvanced.DI.AppScope;
import com.example.user.weatherappadvanced.WeatherAppTest.testWeatherAPI.MockWeatherAPI;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 05/03/2018.
 */

@Module
public class TestMockAppModule {
    private final Context context;

    public TestMockAppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @AppScope
    public Context provideAppContext() {
        return context;
    }

    @Provides public WeatherAPI provideWeatherApiClient() {
        return new MockWeatherAPI();
    }
}
