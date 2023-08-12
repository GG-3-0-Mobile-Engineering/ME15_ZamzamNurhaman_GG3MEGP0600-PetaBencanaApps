package com.example.petabencana

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petabencana.adapter.ReportsAdapter
import com.example.petabencana.api.ApiResponse
import com.example.petabencana.api.Feature
import com.example.petabencana.api.RetrofitClient
import com.example.petabencana.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val list = ArrayList<Feature>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsList = findViewById<RecyclerView>(R.id.news_list)
        newsList.setHasFixedSize(true)
        newsList.layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getReports(admin="ID-JB").enqueue(object : Callback<ApiResponse> {

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                response.body()?.let {
                    list.addAll(it.result.features ?: emptyList())
                }
                val adapter = ReportsAdapter(list)
                newsList.adapter = adapter
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}