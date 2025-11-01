package com.example.travelwise.models

import androidx.annotation.DrawableRes

data class Destination(
    val id: Int,
    val name: String,
    val location: String,
    val price: Double,
    val rating: Float,
    @DrawableRes val imageResource: Int,
    val description: String,
    var isFavorite: Boolean = false
)