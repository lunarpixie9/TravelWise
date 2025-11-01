package com.example.travelwise.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelwise.models.Destination
import com.example.travelwise.R
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _destinations = MutableLiveData<List<Destination>>()
    val destinations: LiveData<List<Destination>> = _destinations

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> = _location

    init {
        _location.value = "Bangalore, India"
    }

    fun loadDestinations(filter: String) {
        viewModelScope.launch {
            // Simulate loading destinations based on filter
            val destinations = getSampleDestinations(filter)
            _destinations.value = destinations
        }
    }

    private fun getSampleDestinations(filter: String): List<Destination> {
        return when (filter) {
            "Best Offers" -> listOf(
                Destination(
                    id = 1,
                    name = "Matsumoto Castle",
                    location = "Osaka, Japan",
                    price = 130.0,
                    rating = 4.9f,
                    imageResource = R.drawable.image1,
                    description = "Historic Japanese castle"
                ),
                Destination(
                    id = 2,
                    name = "Santorini Beach",
                    location = "Greece",
                    price = 250.0,
                    rating = 4.8f,
                    imageResource = R.drawable.image2,
                    description = "Beautiful Mediterranean coast"
                ),
                Destination(
                    id = 3,
                    name = "Paris Landmark",
                    location = "Paris, France",
                    price = 150.0,
                    rating = 4.7f,
                    imageResource = R.drawable.image3,
                    description = "Iconic city landmark"
                )
            )
            "Trending" -> listOf(
                Destination(
                    id = 4,
                    name = "Kyoto Temple",
                    location = "Kyoto, Japan",
                    price = 120.0,
                    rating = 4.9f,
                    imageResource = R.drawable.image4,
                    description = "Ancient temple complex"
                ),
                Destination(
                    id = 5,
                    name = "Beach Resort",
                    location = "Bali, Indonesia",
                    price = 180.0,
                    rating = 4.6f,
                    imageResource = R.drawable.image5,
                    description = "Tropical paradise"
                )
            )
            "Featured" -> listOf(
                Destination(
                    id = 1,
                    name = "Matsumoto Castle",
                    location = "Osaka, Japan",
                    price = 130.0,
                    rating = 4.9f,
                    imageResource = R.drawable.image1,
                    description = "Historic Japanese castle"
                )
            )
            "Top Visit" -> listOf(
                Destination(
                    id = 3,
                    name = "Paris Landmark",
                    location = "Paris, France",
                    price = 150.0,
                    rating = 4.7f,
                    imageResource = R.drawable.image3,
                    description = "Iconic city landmark"
                ),
                Destination(
                    id = 2,
                    name = "Santorini Beach",
                    location = "Greece",
                    price = 250.0,
                    rating = 4.8f,
                    imageResource = R.drawable.image2,
                    description = "Beautiful Mediterranean coast"
                )
            )
            else -> emptyList()
        }
    }
}