package com.example.petabencana.presentation.ui.reports

import ProvinceData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petabencana.domain.models.Province
import com.example.petabencana.domain.repository.ReportState
import com.example.petabencana.domain.usecase.GetReportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val getReportsUseCase: GetReportsUseCase
) : ViewModel() {

    private var dataProvince: ArrayList<Province> = ProvinceData().dataProvince()
    private val _reports = MutableLiveData<ReportState>()

    val reports: LiveData<ReportState> get() = _reports

    init {
        getReports(null, null)
    }


    fun getReports(newProvinceId: String?, disasterType:String?) {
        var idProvince :String? = null
        if (newProvinceId != null) {
            val province: Province? =
                dataProvince.firstOrNull { province: Province -> province.name == newProvinceId }

            if(province != null){
                idProvince = province.id
            }
        }
        viewModelScope.launch {
            getReportsUseCase.execute(idProvince, disasterType).collect { result ->
                _reports.value = result
            }
        }
    }
}