package com.example.user.weatherappadvanced.model;

/**
 * Created by User on 18/02/2018.
 */

public class Sys {
    private Integer type;
    private Integer id;
    private String country;
    private Long sunrise;
    private Long sunset;

    public Sys(String country, Long sunrise, Long sunset) {
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

}