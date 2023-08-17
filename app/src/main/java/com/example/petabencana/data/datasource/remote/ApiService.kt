package com.example.petabencana.data.datasource.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("reports")
    suspend fun getReports(
        @Query("timeperiod") timeperiod: Int? = 604800,
        @Query("geoformat") geoFormat: String? = "geojson",
        @Query("admin") provinceId: String? = null,
        @Query("disaster") disaterType: String? = null
    ): Response<ApiResponse>
}