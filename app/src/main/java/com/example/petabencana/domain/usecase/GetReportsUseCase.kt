package com.example.petabencana.domain.usecase

import com.example.petabencana.domain.repository.ReportState
import kotlinx.coroutines.flow.Flow

interface GetReportsUseCase {
    fun execute(provinceId: String?, disasterType: String?): Flow<ReportState>
}