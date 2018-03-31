package com.example.user.weatherappadvanced.Activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.weatherappadvanced.R;
import com.example.user.weatherappadvanced.model.WeatherData;
import com.example.user.weatherappadvanced.view.FormatWeatherView;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.Locale;

import butterknife.BindView;


public class FormatWeather extends MainActivity implements FormatWeatherView {

    @BindView(R.id.city_id) TextView city_name;
    @BindView(R.id.country_id)  TextView country_name;
    @BindView(R.id.coords_id)  TextView coords;
    @BindView(R.id.temp_id)  TextView temp;
    @BindView(R.id.sunrise_id)  TextView sunrise;
    @BindView(R.id.sunset_id)  TextView sunset;
    @BindView(R.id.cod_id) TextView cod;
    @BindView(R.id.wind_id)  TextView wind;
    @BindView(R.id.clouds_per_id)  TextView clouds;
    @BindView(R.id.main_id)  TextView wmain;
    @BindView(R.id.desc_id) TextView wdesc;
    @BindView(R.id.id_w_icon) ImageView icon;
    private MainActivity mInstance;
    protected String icon_tag="09d";
    public FormatWeather(MainActivity mainActivity) {
        super();
        this.mInstance=mainActivity;
    }

    public void displayWeather(WeatherData weatherData) {
        String Text ="City:" + weatherData.getName();             //City Name
        mInstance.city_name.setText(Text);
        Text ="Country:" + weatherData.getCountry();              //Country Name
        mInstance.country_name.setText(Text);
        Text = "Coordinates:" +"(" + weatherData.getLat() + "," + weatherData.getLon() + ")";       //Coordinates
        mInstance.coords.setText(Text);
        Text = "COD:"+ weatherData.getCod();                           //Code
        mInstance.cod.setText(Text);
        if(mInstance.metric_flag) {
            Text = String.format(Locale.UK, "Temperature: %.2f C", weatherData.getTemp());
        }
        else {
            Text = String.format(Locale.UK, "Temperature: %.2f F", weatherData.getTemp());        //Temperature in Celsius
        }
        mInstance.temp.setText(Text);
        Text="Weather status:"+weatherData.getWeatherMain();           //Weather main status
        mInstance.wmain.setText(Text);
        Text="Weather Description:"+weatherData.getWeatherDesc();      //Weather description
        mInstance.wdesc.setText(Text);
        if(mInstance.metric_flag) {
            Text="Wind Speed:"+weatherData.getWindSpd()+" m/s, Degrees:"+weatherData.getWindDeg();         //Wind speed in m/s
        }
        else {
            Text = "Wind Speed:" + weatherData.getWindSpd() + " y/s, Degrees:" + weatherData.getWindDeg();         //Wind speed in m/s
        }
        mInstance.wind.setText(Text);
        Text="Cloud Percentage:"+weatherData.getCloudPer()+"%";                //Clouds percentage
        mInstance.clouds.setText(Text);

        Date d_sunrise = new Date(weatherData.getSunrise() * 1000);           //Sunrise date up to seconds
        Date d_sunset = new Date(weatherData.getSunset() * 1000);              //Sunset date up to seconds
        Text = "Sunrise:" + local.format(d_sunrise);
        mInstance.sunrise.setText(Text);
        Text = "Sunset:" + local.format(d_sunset);
        mInstance.sunset.setText(Text);
        Text=weatherData.getWeatherIcon();
        String iconURL="http://openweathermap.org/img/w/"+Text+".png";       //Weather icon ID information
        icon_tag=iconURL;
        Picasso.with(mInstance.getBaseContext()).load(iconURL).into(mInstance.icon);
        logQueriedData(weatherData);

    }
    public void ClearWeather()
    {
        String Text="";
        mInstance.city_name.setText(Text);
        mInstance.country_name.setText(Text);
        mInstance.coords.setText(Text);
        mInstance.cod.setText(Text);
        mInstance.temp.setText(Text);
        mInstance.wmain.setText(Text);
        mInstance.wdesc.setText(Text);
        mInstance.wind.setText(Text);
        mInstance.clouds.setText(Text);
        mInstance.sunrise.setText(Text);
        mInstance.sunset.setText(Text);
        mInstance.icon.setVisibility(View.INVISIBLE);
    }
    public void logQueriedData(WeatherData weatherData)
    {
        Date d_sunrise = new Date(weatherData.getSunrise() * 1000);           //Sunrise date up to seconds
        Date d_sunset = new Date(weatherData.getSunset() * 1000);              //Sunset date up to seconds
        String TAG = "WeatherTest";
        String temp;
        if(mInstance.metric_flag) {
            temp = String.format(Locale.UK, "Temperature: %.2f C", weatherData.getTemp());
        }
        else {
            temp = String.format(Locale.UK, "Temperature: %.2f F", weatherData.getTemp());
        }
        Log.d(TAG, "\nExecution success.. Name:" + weatherData.getName()
                + ", Coordinates: (Latitude: " + weatherData.getLat() + ", Longtitude: " + weatherData.getLon() + " ), "
                + temp +
                " Pressure: " + weatherData.getPressure() + ", Humidity:" + weatherData.getHumidity() +
                " Sunrise: " + local.format(d_sunrise) + ", Sunset:" + local.format(d_sunset)  +
                " Country: " + weatherData.getCountry() + ", COD: " + weatherData.getCod());
    }

}
