package com.example.orderapp.Repository.Network;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIClient {

    @POST("api/v1/clean/address")
    @Headers({
            "Content-Type: application/json"
    })
    Call<CoordinatesLogic.CoordinatesResponse> coordinatesL(@Body CoordinatesLogic.CoordinatesRequest request, @Header("Authorization") String token);

}
