package com.example.user.weatherappadvanced.model;

import java.util.List;

/**
 *
 * {"coord":
 {"lon":145.77,"lat":-16.92},
 "weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04n"}],
 "base":"cmc stations",
 "main":{"temp":293.25,"pressure":1019,"humidity":83,"temp_min":289.82,"temp_max":295.37},
 "wind":{"speed":5.1,"deg":150},
 "clouds":{"all":75},
 "rain":{"3h":3},
 "dt":1435658272,
 "sys":{"type":1,"id":8166,"message":0.0166,"country":"AU","sunrise":1435610796,"sunset":1435650870},
 "id":2172797,
 "name":"Cairns",
 "cod":200}
 */

public class WeatherData {

    private Coord coord;
    private Integer cod;
    private String base;
    private String name;
    private Main main;
    private List<Weather> weather;
    private Sys sys;
    private Wind wind;
    private Clouds clouds;



    public WeatherData(Coord coords, Integer code, String base, String name, Main main, List<Weather> weather, Sys sys,Wind wind,Clouds clouds) {
        this.coord = coords;
        this.cod = code;
        this.base = base;
        this.name = name;
        this.main = main;
        this.weather= weather;
        this.sys=sys;
        this.wind=wind;
        this.clouds=clouds;
    }


    public Integer getCod() {
        return cod;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    //-----------Main get methods------------

    public Double getLat() {
        return coord.getLat();
    }

    public Double getLon() {
        return coord.getLon();
    }

    public Double getPressure() {
        return main.getPressure();
    }

    public Double getHumidity(){
        return main.getHumidity();
    }

    public Double getTemp() {
        return main.getTemp();
    }

    public String getCountry() {
        return sys.getCountry();
    }

    public Long getSunrise() {
        return sys.getSunrise();
    }

    public Long getSunset() {
        return sys.getSunset();
    }

    public String getWeatherMain() {return weather.get(0).getMain();}

    public String getWeatherDesc() {return weather.get(0).getDescription();}

    public String getWeatherIcon() {return weather.get(0).getIcon();}

    public Double getWindSpd() {return wind.getSpeed(); }

    public Double getWindDeg() {return wind.getDeg();}

    public Integer getCloudPer() {return clouds.getAll();}








}
