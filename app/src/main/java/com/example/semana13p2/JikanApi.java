package com.example.semana13p2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JikanApi {
    @GET("anime")
    Call<String> getAnimes();
}
