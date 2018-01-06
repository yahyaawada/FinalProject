package com.example.yahya.finalproject;


/**
 * Created by Jason on 04/01/2018.
 */

public class Automobile {

    private String price;
    private String litres;
    private String kilometers;

    // Constructor

    public Automobile(String price,String litres, String kilometers){
        this.price = price;
        this.litres = litres;
        this.kilometers = kilometers;
    }

    // Getters

    public String getPrice() {
        return price;
    }

    public String getLitres() {
        return litres;
    }

    public String getKilometers() {
        return kilometers;
    }

    // Setters

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLitres(String litres) {
        this.litres = litres;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }
}