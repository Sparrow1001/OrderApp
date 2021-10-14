package com.example.orderapp.Repository.Network;

import com.example.orderapp.Repository.Model.Coordinate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIClient {

    @GET("address.php?")
    Call<List<Coordinate>> getCoordinateFromAPI(@Query("key") String key, @Query("term") String address, @Query("fields")String fields);

}
