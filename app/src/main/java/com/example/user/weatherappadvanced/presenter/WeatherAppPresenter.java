package com.example.user.weatherappadvanced.presenter;


import android.util.Log;

import com.example.user.weatherappadvanced.API.WeatherAPI;
import com.example.user.weatherappadvanced.model.WeatherData;
import com.example.user.weatherappadvanced.view.WeatherAppView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by User on 03/03/2018.
 */

public class WeatherAppPresenter extends MvpBasePresenter<WeatherAppView> {


    public void ObtainWeather(WeatherAPI weatherAPI,String city,Boolean metric_flag) {
        if(getView()==null)
        {
            return;
        }
        getView().showLoad();
        String units;
        if(metric_flag)
        {
            units=WeatherAPI.UNITS_METRIC;
        }
        else {
            units=WeatherAPI.UNITS_IMPERIAL;
        }
        weatherAPI.getWeather(city, units, WeatherAPI.API_KEY)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<WeatherData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            getView().showErr(e.getMessage());
                            Log.d("err_tag",e.getMessage());
                        }

                        @Override
                        public void onNext(WeatherData weatherData) {
                            getView().onWeatherObtained(weatherData);
                        }
                    });
        }

}
