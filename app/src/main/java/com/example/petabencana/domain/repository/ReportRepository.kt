package com.example.petabencana.domain.repository

import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    fun getReports(provinceName: String? , disasterType: String?) : Flow<ReportState>
}