package com.example.petabencana.domain.usecase

import com.example.petabencana.domain.repository.ReportRepository
import com.example.petabencana.domain.repository.ReportState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReportsImpl  @Inject constructor(private val repository: ReportRepository ) : GetReportsUseCase{
    override fun execute(provinceId: String? , disasterType: String?): Flow<ReportState> {
        return repository.getReports(provinceId, disasterType)
    }

}