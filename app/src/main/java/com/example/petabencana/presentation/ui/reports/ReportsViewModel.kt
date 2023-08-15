package com.example.petabencana.presentation.ui.reports

import ProvinceData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petabencana.data.datasource.remote.ApiResponse
import com.example.petabencana.data.datasource.RetrofitClient
import com.example.petabencana.domain.models.Province
import com.example.petabencana.domain.models.Report
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class ReportApiStatus { LOADING, ERROR, DONE }

class ReportsViewModel : ViewModel() {

    private val dataProvince: ArrayList<Province> = ProvinceData().dataProvince()

    private val _reports = MutableLiveData<List<Report>>()
    private val _status = MutableLiveData<ReportApiStatus>()

    val reports: LiveData<List<Report>> get() = _reports
    val status: LiveData<ReportApiStatus> get() = _status
    init {
        Log.d("DATA", dataProvince.toString())
        getReports()
    }


    fun getReports(newProvince :String?=null){
        var idProvince = "ID-JK"
        if(newProvince != null){
            val province :Province = dataProvince.first { province: Province -> province.name == newProvince }
            idProvince = province.id
            Log.d("PROVINCE ID", idProvince)
        }
        viewModelScope.launch {

            _status.value = ReportApiStatus.LOADING
            try {
                 RetrofitClient.instance.getReports(province = idProvince).enqueue(object :Callback<ApiResponse>{
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if(response.isSuccessful){
                            _status.value = ReportApiStatus.DONE
                            _reports.value = response.body()?.result?.reports ?: emptyList()
                        }else{
                            _status.value = ReportApiStatus.ERROR
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Log.e("RESPONSE" , t.message.toString())
                        _status.value = ReportApiStatus.ERROR
                    }

                })

            }catch (e: Exception){
                Log.e("RESPONSE", e.message.toString())
                _reports.value = ArrayList()
                _status.value = ReportApiStatus.ERROR
            }
        }
    }
}