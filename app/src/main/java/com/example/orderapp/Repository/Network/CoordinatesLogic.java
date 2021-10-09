package com.example.orderapp.Repository.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoordinatesLogic {

    private APIClient api;

    public CoordinatesLogic(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dadata.ru/api/geocode/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(APIClient.class);
    }

    static class CoordinatesRequest{
        String query;

        public CoordinatesRequest(String query) {
            this.query = query;
        }
    }

    static class CoordinatesResponse{

        double lon;
        double lat;

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

    }

}


