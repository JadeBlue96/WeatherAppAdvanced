package com.example.user.weatherappadvanced.model;

/**
 * Created by User on 18/02/2018.
 */

public class Coord {

    private Double lat;
    private Double lon;

    public Coord(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }
}


