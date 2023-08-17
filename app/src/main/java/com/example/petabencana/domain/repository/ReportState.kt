package com.example.petabencana.domain.repository

import com.example.petabencana.domain.models.Report

sealed class ReportState {
    object Loading: ReportState()
    class Finished(val data: List<Report>) : ReportState()
    class Error(val message: String) : ReportState()
}