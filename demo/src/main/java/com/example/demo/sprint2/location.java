package com.example.demo.sprint2;

public class location {
    private double lat;
    private double lng;

    public location() {
        this.lat = 34.2503;
        this.lng = 25.6847;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
