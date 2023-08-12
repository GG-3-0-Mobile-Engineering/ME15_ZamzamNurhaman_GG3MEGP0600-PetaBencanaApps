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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private val list = ArrayList<Feature>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsList = findViewById<RecyclerView>(R.id.news_list)
        newsList.setHasFixedSize(true)
        newsList.layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getReports(admin = "ID-JB").enqueue(object : Callback<ApiResponse> {

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

        val mapsFragment = supportFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment

        mapsFragment.getMapAsync(this)
    }


    override fun onMapReady(maps: GoogleMap) {
        maps.clear()
//        -6.901733255898841, 107.61896566917964
        val latlang = LatLng(-6.901733255898841, 107.61896566917964)
        maps.animateCamera(CameraUpdateFactory.newLatLngZoom(latlang, 8f))
        val markerOpt = MarkerOptions()
            .position(latlang)
            .title("My Location")

        maps.addMarker(markerOpt)

    }
}