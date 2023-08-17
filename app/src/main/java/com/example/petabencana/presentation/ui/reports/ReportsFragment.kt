package com.example.petabencana.presentation.ui.reports

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petabencana.R
import com.example.petabencana.databinding.FragmentReportsBinding
import com.example.petabencana.domain.models.Report
import com.example.petabencana.domain.repository.ReportState
import com.example.petabencana.presentation.ui.setting.SettingViewModel
import com.example.petabencana.utils.helper.DisasterTypeHelper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.R.layout.support_simple_spinner_dropdown_item
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportsFragment : Fragment(), OnMapReadyCallback {
    private val viewModel: ReportsViewModel by viewModels()
    private val settingViewModel: SettingViewModel by viewModels()
    private lateinit var _binding: FragmentReportsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var gMaps: GoogleMap
    private lateinit var sheetBehavior: BottomSheetBehavior<View>

    private var provinceId: String? = null
    private var disasterType: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleBottomSheet()

        //maps
        val mapsFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapsFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        _binding.btnSetting.setOnClickListener {
            val action = ReportsFragmentDirections.actionReportsFragmentToSettingFragment()
            view.findNavController().navigate(action)
        }

        _binding.autoCompleteTextView.setAdapter(
            ArrayAdapter(
                requireActivity(),
                support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.province)
            )
        )
        _binding.autoCompleteTextView.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && textView.text.isNotEmpty()) {
                provinceId = textView.text.toString()
                val keyboard = requireActivity().getSystemService(InputMethodManager::class.java)
                keyboard.hideSoftInputFromWindow(textView.windowToken, 0)

                viewModel.getReports(provinceId, disasterType)

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        viewModel.reports.observe(viewLifecycleOwner) { state ->
            when (state) {
                ReportState.Loading -> _binding.progressCircular.visibility = View.VISIBLE
                is ReportState.Error -> _binding.progressCircular.visibility = View.GONE
                is ReportState.Finished -> {
                    _binding.progressCircular.visibility = View.GONE
                    val listAdapter = ReportsAdapter(requireActivity(), state.data)
                    val layout = LinearLayoutManager(requireActivity())
                    _binding.newsList.apply {
                        adapter = listAdapter
                        layoutManager = layout
                    }
                }
            }
        }

        _binding.radioGroupReport.apply {
            check(_binding.radioButtonAll.id)
            setOnCheckedChangeListener { _, checkedId ->
                val radioButton = findViewById<RadioButton>(checkedId)
                val selectedType = radioButton.text
                disasterType = if (radioButton.id == R.id.radioButtonAll) {
                    null
                } else {
                    DisasterTypeHelper.getDisasterTypewithEnum(selectedType.toString())
                }
                viewModel.getReports(provinceId, disasterType)
            }
        }

        requestLocationPermission()

    }

    private fun handleBottomSheet() {
        val layoutSheet = _binding.bottomSheetLayout
        val arrowUpButton = _binding.arrowUpIcon
        val closeIcon = _binding.closeIcon

        sheetBehavior = BottomSheetBehavior.from(layoutSheet)
        sheetBehavior.maxHeight = (getScreenHeight() * 0.98).toInt()

        arrowUpButton.setOnClickListener {

            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

            } else {
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        closeIcon.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        sheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                arrowUpButton.rotation = slideOffset * 180
                if (slideOffset < 0.0) {
                    sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }

                if (slideOffset >= 1.0) {
                    closeIcon.visibility = View.VISIBLE
                } else {
                    closeIcon.visibility = View.GONE
                }
            }

        })
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val myLocation = LatLng(location.latitude, location.longitude)
                    gMaps.addMarker(MarkerOptions().position(myLocation).title("Me"))
                    gMaps.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10.0F))
                }
            }
        }
    }

    override fun onMapReady(googleMaps: GoogleMap) {
        gMaps = googleMaps
        getCurrentLocation()
        settingViewModel.isDarkTheme().observe(viewLifecycleOwner) { isDarkMode ->
            when (isDarkMode) {
                true -> gMaps.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        requireActivity(),
                        R.raw.map_style_dark
                    )
                )

                else -> gMaps.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        requireActivity(),
                        R.raw.map_style_light
                    )
                )
            }
        }

        viewModel.reports.observe(viewLifecycleOwner) { state ->
            gMaps.clear()
            when (state) {
                is ReportState.Finished -> {
                    val dataReport = state.data
                    if (_binding.autoCompleteTextView.text!!.isNotEmpty() && dataReport.isNotEmpty()) {
                        val coordinate = dataReport[0].geometry.coordinates
                        val positionFirsReport = LatLng(coordinate[1], coordinate[0])
                        gMaps.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                positionFirsReport,
                                10.0f
                            )
                        )
                    }
                    for (report: Report in dataReport) {
                        val header = report.properties.disasterType
                        val position =
                            LatLng(report.geometry.coordinates[1], report.geometry.coordinates[0])

                        val markerOpt = MarkerOptions()
                            .position(position)
                            .title(report.properties.title ?: "Bencana")
                            .snippet(header)

                        gMaps.addMarker(markerOpt)
                    }
                }

                else -> {}
            }
        }


    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}