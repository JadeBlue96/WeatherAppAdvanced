package com.example.user.weatherappadvanced.Activity;

import android.app.Activity;
import android.content.SharedPreferences;

public class SharedCityPreference {
    SharedPreferences preferences;
    public SharedCityPreference(MainActivity activity)
    {
        preferences=activity.getPreferences(Activity.MODE_PRIVATE);
    }
    String getCity() {return preferences.getString("city","Burgas");}
    void setCity(String city) {preferences.edit().putString("city",city).apply();}
    Boolean getMetric() {return preferences.getBoolean("metric",true);}
    void setMetric(Boolean metric) {preferences.edit().putBoolean("metric",metric).apply();}

}
