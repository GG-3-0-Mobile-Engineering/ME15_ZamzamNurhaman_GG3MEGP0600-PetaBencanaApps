package com.example.petabencana.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DisasterApi {
    @GET("reports")
    fun getReports(
        @Query("timeperiod") timeperiod: Int? = 604800,
        @Query("geoformat") geoFormat: String? = "geojson",
//        @Query("admin") admin: String? = "ID-JK"
    ): Call<ApiResponse>
}