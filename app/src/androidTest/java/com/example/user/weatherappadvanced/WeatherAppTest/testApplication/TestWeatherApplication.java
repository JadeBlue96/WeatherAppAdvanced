package com.example.user.weatherappadvanced.WeatherAppTest.testApplication;

import com.example.user.weatherappadvanced.Application.WeatherApp;
import com.example.user.weatherappadvanced.WeatherAppTest.TestDI.DaggerTestMockAppComponent;
import com.example.user.weatherappadvanced.WeatherAppTest.TestDI.TestMockAppComponent;
import com.example.user.weatherappadvanced.WeatherAppTest.TestDI.TestMockAppModule;

/**
 * Created by User on 05/03/2018.
 */

public class TestWeatherApplication extends WeatherApp{
    private TestMockAppComponent testWeatherAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        testWeatherAppComponent = DaggerTestMockAppComponent.builder()
                .testMockAppModule(new TestMockAppModule(this))
                .build();
    }

    @Override
    public TestMockAppComponent appComponent() {
        return testWeatherAppComponent;
    }
}
