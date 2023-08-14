package com.example.petabencana.data.datasource

import com.example.petabencana.data.datasource.remote.DisasterApi
import com.example.petabencana.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    val instance: DisasterApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(DisasterApi::class.java)
    }
}