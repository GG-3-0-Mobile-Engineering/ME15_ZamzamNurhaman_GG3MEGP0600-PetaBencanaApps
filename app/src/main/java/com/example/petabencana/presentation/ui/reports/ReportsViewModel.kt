package com.example.petabencana.presentation.ui.reports

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petabencana.data.dataSource.remote.ApiResponse
import com.example.petabencana.data.dataSource.RetrofitClient
import com.example.petabencana.domain.models.Report
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class ReportApiStatus { LOADING, ERROR, DONE }

class ReportsViewModel : ViewModel() {


    private val _reports = MutableLiveData<List<Report>>()
    private val _status = MutableLiveData<ReportApiStatus>()

    val reports: LiveData<List<Report>> get() = _reports
    val status: LiveData<ReportApiStatus> get() = _status
    init {
        getReports()
    }


    fun getReports(newProvince :String?=null){

        viewModelScope.launch {
            _status.value = ReportApiStatus.LOADING
            try {
                 RetrofitClient.instance.getReports(province = newProvince ?: "ID-JK").enqueue(object :Callback<ApiResponse>{
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if(response.isSuccessful){
                            _status.value = ReportApiStatus.DONE
                            _reports.value = response.body()?.result?.reports ?: emptyList()
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