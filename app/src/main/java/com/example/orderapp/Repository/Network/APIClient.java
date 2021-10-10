package com.example.orderapp.Repository.Network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIClient {

    @GET("address.php?key=s9CEluxAEtMl&term=Омск%20ленина%2021%20&fields=value,geo_center")
    Call<Coordinate> getCoordinateFromAPI();

}
