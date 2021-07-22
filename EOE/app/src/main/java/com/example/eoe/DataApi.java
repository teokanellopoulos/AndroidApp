package com.example.eoe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DataApi {

    @Headers("Authorization:Token 6f7afbe6fa48a0c2721ed86c3dda8fd633eeb120")
    @GET("mdg_emvolio")
    Call<List<CityStats>> getCityStats(@Query("date_from") String date_from, @Query("date_to") String date_to);
}
