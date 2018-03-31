package com.example.user.weatherappadvanced.model;

/**
 * Created by User on 18/02/2018.
 */

public class Weather {

    private Integer id;
    private String main;
    private String description;
    private String icon;

    public Weather(String main) {
        this.main = main;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}