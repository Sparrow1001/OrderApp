package com.example.orderapp.Repository.Network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoordinatesLogic {

    private APIClient api;

    public CoordinatesLogic(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.geotree.ru/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(APIClient.class);
    }


    public LiveData<String> getCoordinate(){
        MutableLiveData<String> coordinates = new MutableLiveData<>();

        Call<Coordinate> call = api.getCoordinateFromAPI();
        call.enqueue(new Callback<Coordinate>() {
            @Override
            public void onResponse(Call<Coordinate> call, Response<Coordinate> response) {
                coordinates.setValue(response.body().getValue());
                Log.e("qqqqqqqqqqqqqq",response.body().getValue());
            }

            @Override
            public void onFailure(Call<Coordinate> call, Throwable t) {
                Log.e("ffffffffff", "ddddddddddddd"+t+call);
            }
        });

        return coordinates;
    }


}


