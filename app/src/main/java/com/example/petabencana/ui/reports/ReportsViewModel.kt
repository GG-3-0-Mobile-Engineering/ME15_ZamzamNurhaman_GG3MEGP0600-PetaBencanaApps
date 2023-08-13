package com.example.petabencana.ui.reports

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petabencana.api.ApiResponse
import com.example.petabencana.api.Report
import com.example.petabencana.api.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class ReportApiStatus { LOADING, ERROR, DONE }

class ReportsViewModel : ViewModel() {


    private val _reports = MutableLiveData<List<Report>>()
    private val _status = MutableLiveData<ReportApiStatus>()

    val reports: LiveData<List<Report>> = _reports
    val status: LiveData<ReportApiStatus> = _status
    init {
        Log.d("RESPONSE", "INIT")
        getReports()
    }


    private fun getReports(){
        Log.d("RESPONSE", "GET REPORTS")
        viewModelScope.launch {
            _status.value = ReportApiStatus.LOADING
            try {
                 RetrofitClient.instance.getReports().enqueue(object :Callback<ApiResponse>{
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if(response.isSuccessful){
                            Log.d("RESPONSE", response.body().toString())
                            _reports.value = response.body()?.result?.reports ?: emptyList()
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Log.e("Response" , t.message.toString())
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