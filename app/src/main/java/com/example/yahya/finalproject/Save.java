package com.example.yahya.finalproject;

/**
 * Created by root on 03/01/18.
 */

public class Save {
    private String date;
    private String time;
    private String temperature;

    public Save(String date, String time, String temperature) {
        this.date = date;
        this.time = time;
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}

