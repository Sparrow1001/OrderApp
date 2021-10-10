package com.example.orderapp.Repository.Network;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoordinatesLogic {

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private APIClient api;

    public CoordinatesLogic(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.geotree.ru/")
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(APIClient.class);
    }


    public LiveData<Geo_center> getCoordinate(String query){

        MutableLiveData<Geo_center> coordinates = new MutableLiveData<>();

        Call<List<Coordinate>> call = api.getCoordinateFromAPI(query, "value,geo_center");
        call.enqueue(new Callback<List<Coordinate>>() {
            @Override
            public void onResponse(Call<List<Coordinate>> call, Response<List<Coordinate>> response) {
                if (response.isSuccessful() && response.body() != null){
                    coordinates.setValue(response.body().get(0).getGeo_center());
                }

            }
            @Override
            public void onFailure(Call<List<Coordinate>> call, Throwable t) {
                Log.e("ffffffffff", "ddddddddddddd"+t+call);
            }
        });

        return coordinates;
    }


}


