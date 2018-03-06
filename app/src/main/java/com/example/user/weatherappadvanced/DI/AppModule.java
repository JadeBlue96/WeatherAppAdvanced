package com.example.user.weatherappadvanced.DI;

import android.content.Context;

import com.example.user.weatherappadvanced.API.WeatherAPI;
import com.example.user.weatherappadvanced.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by User on 05/03/2018.
 */

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @AppScope public Context provideAppContext() {
        return context;
    }

    @Provides public WeatherAPI provideWeatherApiClient() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(WeatherAPI.BASE_URL)
                .build()
                .create(WeatherAPI.class);
    }
}
