package com.app.equipe.controiai2.domain;

/**
 * Created by Matt on 24/07/2018.
 */

public class Precisa {

    private String brand;
    private int photo;

    public Precisa(){}
    public Precisa( String b, int p){

        brand = b;
        photo = p;
    }



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

}
