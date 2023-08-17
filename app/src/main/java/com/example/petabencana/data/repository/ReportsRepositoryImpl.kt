package com.example.petabencana.data.repository

import com.example.petabencana.data.datasource.remote.ApiService
import com.example.petabencana.domain.repository.ReportRepository
import com.example.petabencana.domain.repository.ReportState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class ReportsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ReportRepository {
    override fun getReports(provinceName: String?, disasterType: String?): Flow<ReportState> {
        return flow {
            emit(ReportState.Loading)
            try {
                val response =
                    apiService.getReports(provinceId = provinceName, disaterType = disasterType)
                if (response.isSuccessful) {
                    emit(ReportState.Finished(response.body()!!.result.reports!!))
                } else {
                    emit(ReportState.Error("Failed to get Reports"))
                }
            } catch (e: Exception) {
                emit(ReportState.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}