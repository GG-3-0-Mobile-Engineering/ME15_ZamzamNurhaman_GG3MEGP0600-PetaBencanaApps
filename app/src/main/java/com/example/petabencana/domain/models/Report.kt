package com.example.petabencana.domain.models

import com.google.gson.annotations.SerializedName

data class Report(
    val type: String,
    val geometry: Geometry,
    val properties: Properties
)

data class Geometry (
    val type: String,
    val coordinates: ArrayList<Double>
)

data class Properties (
    val pkey: String,

    @SerializedName("created_at")
    val createdAt: String,

    val source: String,
    val status: String,
    val url: String,

    @SerializedName("image_url")
    val imageURL: String? = null,

    @SerializedName("disaster_type")
    val disasterType: String,

    @SerializedName("report_data")
    val reportData: ReportData,

    val tags: Tags,
    val title: String?,
    val text: String,

    @SerializedName("partner_code")
    val partnerCode: Any? = null,

    @SerializedName("partner_icon")
    val partnerIcon: Any? = null
)

data class ReportData (
    @SerializedName("report_type")
    val reportType: String,

    val visibility: Long,
    val airQuality: Long
)

data class Tags (
    @SerializedName("district_id")
    val districtID: Any? = null,

    @SerializedName("region_code")
    val regionCode: String,

    @SerializedName("local_area_id")
    val localAreaID: Any? = null,

    @SerializedName("instance_region_code")
    val instanceRegionCode: String
)