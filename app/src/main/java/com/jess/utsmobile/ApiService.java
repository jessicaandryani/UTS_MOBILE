package com.jess.utsmobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("path/to/your/endpoint")
    Call<GoogleApiResponse> getChartData(@Query("key") String apiKey);
}