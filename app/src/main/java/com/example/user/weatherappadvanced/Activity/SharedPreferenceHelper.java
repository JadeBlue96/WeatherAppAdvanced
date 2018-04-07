package com.example.user.weatherappadvanced.Activity;

import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private static final String KEY_CITY_NAME = "key_name";
    private static final String KEY_METRIC = "key_metric";

    private final SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(SharedPreferences m_sharedPreferences)
    {
        sharedPreferences=m_sharedPreferences;
    }

    public static String getKeyCityName() {
        return KEY_CITY_NAME;
    }

    public static String getKeyMetric() {
        return KEY_METRIC;
    }

    public Boolean saveCityInfo(SharedCityPreference sharedCityPreference)
    {
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(KEY_CITY_NAME,sharedCityPreference.getName());
        editor.putBoolean(KEY_METRIC,sharedCityPreference.getMetric());
        return editor.commit();
    }
    public SharedCityPreference getCityInfo()
    {
        String city_name=sharedPreferences.getString(KEY_CITY_NAME,"Burgas");
        Boolean metric=sharedPreferences.getBoolean(KEY_METRIC,true);
        return new SharedCityPreference(city_name,metric);
    }

}
