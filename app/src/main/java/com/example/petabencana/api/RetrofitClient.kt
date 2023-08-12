package com.example.petabencana.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASEURL = "https://data.petabencana.id/"

    val instance: DisasterApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(DisasterApi::class.java)
    }
}