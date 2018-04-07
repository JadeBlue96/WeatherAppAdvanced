package com.example.user.weatherappadvanced.Activity;

import android.app.Activity;
import android.content.SharedPreferences;

public class SharedCityPreference {
    private final String name;
    private final Boolean metric;
    public SharedCityPreference(String m_name,Boolean m_metric)
    {
        name=m_name;
        metric=m_metric;
    }
    //String getCity() {return preferences.getString("city","Burgas");}
    //void setCity(String city) {preferences.edit().putString("city",city).apply();}
   // Boolean getMetric() {return preferences.getBoolean("metric",true);}
    //void setMetric(Boolean metric) {preferences.edit().putBoolean("metric",metric).apply();}
    public String getName() {return name;}
    public Boolean getMetric() {return metric;}

}
