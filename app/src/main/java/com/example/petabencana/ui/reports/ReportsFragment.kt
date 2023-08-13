package com.example.petabencana.ui.reports

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petabencana.R
import com.example.petabencana.databinding.FragmentReportsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ReportsFragment : Fragment(), OnMapReadyCallback {
    private val viewModel: ReportsViewModel by viewModels()
    private lateinit var _binding: FragmentReportsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //maps
        val mapsFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapsFragment.getMapAsync(this)
        Log.e("RESPONSE", "INIT MAPSS")
        viewModel.reports.observe(viewLifecycleOwner){
            Log.e("RESPONSE", "BERHASIL")
            val listAdapter = ReportsAdapter(it)
            val layout = LinearLayoutManager(requireActivity())
            _binding.newsList.apply {
                adapter = listAdapter
                layoutManager = layout
            }
        }

    }

    override fun onMapReady(gMaps: GoogleMap) {
        gMaps.clear()
        val latlang = LatLng(-6.901733255898841, 107.61896566917964)
        gMaps.animateCamera(CameraUpdateFactory.newLatLngZoom(latlang, 8f))
        val markerOpt = MarkerOptions()
            .position(latlang)
            .title("My Location")

        gMaps.addMarker(markerOpt)
    }

}