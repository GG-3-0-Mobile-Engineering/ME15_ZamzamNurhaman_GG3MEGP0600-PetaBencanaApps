package com.example.petabencana.model

import androidx.annotation.DrawableRes

data class News(
    @DrawableRes val imageResourceId: Int,
    val title: String,
    val description: String,
)
