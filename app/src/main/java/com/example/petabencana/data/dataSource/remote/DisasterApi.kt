package com.example.petabencana.data.dataSource.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DisasterApi {
    @GET("reports")
    fun getReports(
        @Query("timeperiod") timeperiod: Int? = 604800,
        @Query("geoformat") geoFormat: String? = "geojson",
        @Query("admin") province: String? = null
    ): Call<ApiResponse>
}