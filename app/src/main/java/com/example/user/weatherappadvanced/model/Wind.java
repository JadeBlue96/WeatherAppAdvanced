package com.example.user.weatherappadvanced.model;

/**
 * Created by User on 18/02/2018.
 */

public class Wind {

    private Double speed;
    private Double deg;

    public Wind(Double speed, Double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public Double getSpeed() {
        return speed;
    }

    public Double getDeg() {
        return deg;
    }
}
