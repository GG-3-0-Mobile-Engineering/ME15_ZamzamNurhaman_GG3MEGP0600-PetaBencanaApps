package com.example.petabencana.data.dataSource.remote

import com.example.petabencana.domain.models.Report
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val  result : Result
)

data class Result(
    val type : String,
    @SerializedName("features")
    val reports : List<Report>? = null
)


