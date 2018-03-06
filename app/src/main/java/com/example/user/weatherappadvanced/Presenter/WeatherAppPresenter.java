package com.example.user.weatherappadvanced.Presenter;


import com.example.user.weatherappadvanced.API.WeatherAPI;
import com.example.user.weatherappadvanced.Adapter.WeatherAdapter;
import com.example.user.weatherappadvanced.Model.WeatherData;
import com.example.user.weatherappadvanced.R;
import com.example.user.weatherappadvanced.View.WeatherAppView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by User on 03/03/2018.
 */

public class WeatherAppPresenter extends MvpBasePresenter<WeatherAppView> {


    public void ObtainWeather(WeatherAPI weatherAPI,String city) {
        if(getView()==null)
        {
            return;
        }

        getView().showLoad();

        weatherAPI.getWeather(city,WeatherAPI.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showErr(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherData weatherData) {
                        getView().onWeatherObtained(weatherData);
                    }
                });
    }
}
