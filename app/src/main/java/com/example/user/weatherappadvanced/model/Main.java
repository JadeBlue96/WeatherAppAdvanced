package com.example.user.weatherappadvanced.model;

/**
 * Created by User on 18/02/2018.
 */

public class Main {
        private Double temp;
        private Double pressure;
        private Double humidity;

        public Main(Double temp, Double pressure, Double humidity) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
    }


        public Double getTemp() {
            return temp;
        }

        public Double getPressure() {
            return pressure;
        }

        public Double getHumidity() {
            return humidity;
        }

}
