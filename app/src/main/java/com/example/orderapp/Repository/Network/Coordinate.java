package com.example.orderapp.Repository.Network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Coordinate {

    private Geo_center geo_center;
    @SerializedName("value")
    private String value;

    public Geo_center getGeo_center ()
    {
        return geo_center;
    }

    public void setGeo_center (Geo_center geo_center)
    {
        this.geo_center = geo_center;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    List<Geo_center> geo_centerList;

    @Override
    public String toString() {
        return "Coordinate{" +
                "geo_center=" + geo_center +
                ", value='" + value + '\'' +
                '}';
    }
}
