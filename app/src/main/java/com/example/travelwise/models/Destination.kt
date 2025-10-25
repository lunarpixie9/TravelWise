package com.example.travelwise.models

data class Destination(
    val id: Int,
    val name: String,
    val location: String,
    val price: Double,
    val rating: Float,
    val imageResource: Int,  // Changed from imageUrl: String
    var isFavorite: Boolean = false,
    val description: String = ""
)