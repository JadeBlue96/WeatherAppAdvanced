package com.example.user.weatherappadvanced.WeatherAppTest.TestRunner;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.example.user.weatherappadvanced.WeatherAppTest.TestApplication.TestWeatherApplication;

/**
 * Created by User on 05/03/2018.
 */

public class WeatherTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestWeatherApplication.class.getName(), context);
    }
}
