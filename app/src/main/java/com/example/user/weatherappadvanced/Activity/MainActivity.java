package com.example.user.weatherappadvanced.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.weatherappadvanced.API.WeatherAPI;
import com.example.user.weatherappadvanced.Application.WeatherApp;
import com.example.user.weatherappadvanced.model.WeatherData;
import com.example.user.weatherappadvanced.presenter.WeatherAppPresenter;
import com.example.user.weatherappadvanced.R;
import com.example.user.weatherappadvanced.view.WeatherAppView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends MvpActivity<WeatherAppView,WeatherAppPresenter> implements WeatherAppView {


    @BindView(R.id.input_city_id) EditText in_city_name;
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
    @BindView(R.id.id_w_icon)  ImageView icon;
    @BindView(R.id.err_field) TextView err;
    @BindView(R.id.metric_change) Button m_chg;
    private FormatWeather formatWeather;
    protected Boolean metric_flag;
    //private Unbinder unbinder;
    private ProgressDialog progressDialog;
    DateFormat local = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.UK);

    @Inject
    WeatherAPI weatherAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherApp) getApplication()).appComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.downloading_info));
        m_chg.setText(R.string.celsius);
        formatWeather=new FormatWeather(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if(err.getText().toString().equals(""))
        {
            icon.setVisibility(View.VISIBLE);
        }
        SharedCityPreference preference=new SharedCityPreference(this);
        final String city=preference.getCity();
        final Boolean metric=preference.getMetric();
        metric_flag=metric;
        if(metric_flag)
        {
            m_chg.setText(R.string.celsius);
        }
        else {
            m_chg.setText(R.string.fahrenheit);
        }
        in_city_name.setText(city);
        presenter.ObtainWeather(weatherAPI,city,metric);
        setSupportActionBar(toolbar);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unbinder.unbind();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("savedTextCity", city_name.getText().toString());
        outState.putString("savedTextCountry", country_name.getText().toString());
        outState.putString("savedTextCoords", coords.getText().toString());
        outState.putString("savedTextTemp", temp.getText().toString());
        outState.putString("savedTextSunrise", sunrise.getText().toString());
        outState.putString("savedTextSunset", sunset.getText().toString());
        outState.putString("savedTextCod", cod.getText().toString());
        outState.putString("savedTextWind", wind.getText().toString());
        outState.putString("savedTextClouds", clouds.getText().toString());
        outState.putString("savedTextWmain", wmain.getText().toString());
        outState.putString("savedTextWdesc", wdesc.getText().toString());
        outState.putString("savedTextErr", err.getText().toString());
        outState.putString("savedImage",formatWeather.icon_tag);
        outState.putBoolean("savedFlag",metric_flag);
        outState.putString("savedMetricText",m_chg.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        city_name.setText(savedInstanceState.getString("savedTextCity"));
        country_name.setText(savedInstanceState.getString("savedTextCountry"));
        coords.setText(savedInstanceState.getString("savedTextCoords"));
        temp.setText(savedInstanceState.getString("savedTextTemp"));
        sunrise.setText(savedInstanceState.getString("savedTextSunrise"));
        sunset.setText(savedInstanceState.getString("savedTextSunset"));
        cod.setText(savedInstanceState.getString("savedTextCod"));
        wind.setText(savedInstanceState.getString("savedTextWind"));
        clouds.setText(savedInstanceState.getString("savedTextClouds"));
        wmain.setText(savedInstanceState.getString("savedTextWmain"));
        wdesc.setText(savedInstanceState.getString("savedTextWdesc"));
        formatWeather.icon_tag=savedInstanceState.getString("savedImage");
        Picasso.with(getBaseContext()).load(formatWeather.icon_tag).into(icon);
        if(err.getText()!="")  err.setText("");
        metric_flag=savedInstanceState.getBoolean("savedFlag");
        m_chg.setText(savedInstanceState.getString("savedMetricText"));


    }

    @NonNull
    @Override
    public WeatherAppPresenter createPresenter() {
        return new WeatherAppPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showLoad() {
        progressDialog.show();
    }

    @Override
    public void hideLoad() {
        progressDialog.hide();
    }

    @Override
    public void showErr(String message) {
        hideLoad();
        formatWeather.ClearWeather();
        err.setText(message);
        err.setVisibility(View.VISIBLE);
        //Snackbar.make(rootLayout,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onWeatherObtained(WeatherData weatherData) {
        hideLoad();
        formatWeather.displayWeather(weatherData);
        err.setText("");
        icon.setVisibility(View.VISIBLE);
        SharedCityPreference preference=new SharedCityPreference(this);
        preference.setCity(weatherData.getName());
    }



    @OnClick(R.id.show_weather)
    public void showWeather(View view) {
        hideKeyboard(MainActivity.this,in_city_name.getWindowToken()); //Retrieve a unique token identifying the window this view is attached to
        String city=in_city_name.getText().toString();
        err.setVisibility(View.INVISIBLE);
        if(city.isEmpty())
        {
            showErr(getString(R.string.no_city_entered));
            return;
        }
        presenter.ObtainWeather(weatherAPI,city,metric_flag);
    }

    @OnClick(R.id.metric_change)
    public void switchMetric(View view) {
        if(metric_flag)
        {
            metric_flag=false;
            m_chg.setText(R.string.fahrenheit);
            new SharedCityPreference(this).setMetric(metric_flag);
            showWeather(view);
        }
        else {
            metric_flag=true;
            m_chg.setText(R.string.celsius);
            new SharedCityPreference(this).setMetric(metric_flag);
            showWeather(view);
        }
        err.setVisibility(View.INVISIBLE);
    }


    public void hideKeyboard(Activity activity,
                             IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) activity.getSystemService
                (Context.INPUT_METHOD_SERVICE);   //get a handle for the current service
        if (mgr != null) {
            mgr.hideSoftInputFromWindow(windowToken, 0);  //hide input window for current token 0-HIDE_IMPLICIT_ONLY
        }
    }


}
