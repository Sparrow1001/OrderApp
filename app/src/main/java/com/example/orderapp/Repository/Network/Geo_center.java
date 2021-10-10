package com.example.orderapp.Repository.Network;

public class Geo_center {
    private String lon;

    private String lat;

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Geo_center{" +
                "lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
