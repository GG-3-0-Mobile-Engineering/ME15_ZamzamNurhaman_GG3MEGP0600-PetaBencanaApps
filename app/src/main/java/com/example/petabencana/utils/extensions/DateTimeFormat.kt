package com.example.petabencana.utils.extensions

import java.text.SimpleDateFormat
import java.util.Locale

 class DateTimeFormat {

     fun convertDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE, d MMMM yyyy. HH:mm", Locale.getDefault())
        val parsedDate = inputFormat.parse(date)
        return outputFormat.format(parsedDate!!)
    }
}