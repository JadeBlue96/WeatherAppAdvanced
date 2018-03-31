package com.example.user.weatherappadvanced.WeatherAppTest.Tests;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import com.example.user.weatherappadvanced.API.WeatherAPI;
import com.example.user.weatherappadvanced.Activity.MainActivity;
import com.example.user.weatherappadvanced.model.WeatherData;
import com.example.user.weatherappadvanced.R;
import com.example.user.weatherappadvanced.WeatherAppTest.testApplication.TestWeatherApplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by User on 05/03/2018.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String CITY_NAME = "London";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Inject
    WeatherAPI weatherAPI;

    @Before
    public void setUp() {
        weatherAPI = ((TestWeatherApplication) activityTestRule.getActivity().getApplication()).appComponent()
                .weatherAPI();
    }
    @Test
    public void checkNoCityEnteredSnackbar() {
        onView(withId(R.id.show_weather)).perform(click());
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.no_city_entered)))
                .check(matches(isDisplayed()));
    }
    @Test
    public void correctWeatherDataDisplayed() {
        WeatherData weatherData = weatherAPI.getWeather(CITY_NAME,WeatherAPI.UNITS_METRIC,WeatherAPI.API_KEY).toBlocking().first();
        onView(withId(R.id.input_city_id)).perform(replaceText(CITY_NAME));
        onView(withId(R.id.input_city_id)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.show_weather)).perform(click());
        onView(withId(R.id.city_id)).check(matches(withText("City:" + weatherData.getName())));
        onView(withId(R.id.country_id)).check(matches(withText("Country:" + weatherData.getCountry())));
        onView(withId(R.id.coords_id)).check(matches(withText("Coordinates:" +"(" + weatherData.getLat() + "," + weatherData.getLon() + ")")));
        onView(withId(R.id.cod_id)).check(matches(withText("COD:"+ weatherData.getCod())));
        onView(withId(R.id.temp_id)).check(matches(withText(String.format(Locale.UK, "Temperature: %.2f C", weatherData.getTemp()-273.15))));
        onView(withId(R.id.main_id)).check(matches(withText("Weather status:"+weatherData.getWeatherMain())));
        onView(withId(R.id.desc_id)).check(matches(withText("Weather Description:"+weatherData.getWeatherDesc())));
        onView(withId(R.id.wind_id)).check(matches(withText("Wind Speed:"+weatherData.getWindSpd()+"m/s, Degrees:"+weatherData.getWindDeg())));
        onView(withId(R.id.clouds_per_id)).check(matches(withText("Cloud Percentage:"+weatherData.getCloudPer()+"%")));

        DateFormat local = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.UK);
        Date d_sunrise = new Date(weatherData.getSunrise() * 1000);           //Sunrise date up to seconds
        Date d_sunset = new Date(weatherData.getSunset() * 1000);              //Sunset date up to second

        onView(withId(R.id.sunrise_id)).check(matches(withText("Sunrise:" + local.format(d_sunrise))));
        onView(withId(R.id.sunset_id)).check(matches(withText("Sunset:" + local.format(d_sunset))));
    }






}
