package com.example.travelwise.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelwise.R
import com.example.travelwise.adapters.DestinationAdapter
import com.example.travelwise.databinding.ActivityHomeBinding
import com.example.travelwise.models.Destination
import com.example.travelwise.ui.DestinationDetailActivity
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var destinationAdapter: DestinationAdapter
    private val destinations = mutableListOf<Destination>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Hide action bar
        supportActionBar?.hide()

        // ✅ Get username from intent or show default
        val username = intent.getStringExtra("USERNAME") ?: "Traveler"

        // ✅ Capitalize first letter and set greeting text
        val displayName = username.replaceFirstChar { it.uppercase() }
        binding.tvGreeting.text = "Welcome, $displayName!"

        // ✅ Setup category boxes
        setupCategoryBoxes()

        // ✅ RecyclerView setup
        setupRecyclerView()

        // ✅ Load sample destinations
        loadSampleData()

        // ✅ Bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_trips -> true
                R.id.nav_favorites -> true
                R.id.nav_profile -> true
                else -> false
            }
        }
    }

    private fun setupCategoryBoxes() {
        // Hotels - using View Binding
        binding.boxHotels.ivCategoryIcon.setImageResource(R.drawable.ic_hotel)
        binding.boxHotels.tvCategoryName.text = "Hotels"

        // Flights
        binding.boxFlights.ivCategoryIcon.setImageResource(R.drawable.ic_flight)
        binding.boxFlights.tvCategoryName.text = "Flights"

        // Cars
        binding.boxCars.ivCategoryIcon.setImageResource(R.drawable.ic_car)
        binding.boxCars.tvCategoryName.text = "Cars"

        // Meals
        binding.boxMeals.ivCategoryIcon.setImageResource(R.drawable.ic_meal)
        binding.boxMeals.tvCategoryName.text = "Meals"
    }

    private fun setupRecyclerView() {
        destinationAdapter = DestinationAdapter(destinations) { destination ->
            openDestinationDetail(destination)
        }

        binding.rvDestinations.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = destinationAdapter

            // Add horizontal spacing between cards
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.card_spacing)
            addItemDecoration(object : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: android.graphics.Rect,
                    view: android.view.View,
                    parent: androidx.recyclerview.widget.RecyclerView,
                    state: androidx.recyclerview.widget.RecyclerView.State
                ) {
                    outRect.right = spacingInPixels
                }
            })
        }
    }

    private fun loadSampleData() {
        lifecycleScope.launch {
            destinations.clear()
            destinations.addAll(
                listOf(
                    Destination(
                        id = 1,
                        name = "Matsumoto Castle",
                        location = "Osaka, Japan",
                        price = 130.0,
                        rating = 4.8f,
                        imageResource = R.drawable.image1,
                        description = "Beautiful historic castle"
                    ),
                    Destination(
                        id = 2,
                        name = "Mountain Valley",
                        location = "Las Vegas, US",
                        price = 200.0,
                        rating = 4.9f,
                        imageResource = R.drawable.image2,
                        description = "Stunning mountain views"
                    ),
                    Destination(
                        id = 3,
                        name = "Tokyo Tower",
                        location = "Tokyo, Japan",
                        price = 150.0,
                        rating = 4.7f,
                        imageResource = R.drawable.image3,
                        description = "Iconic city landmark"
                    ),
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
            )

            destinationAdapter.notifyDataSetChanged()
        }
    }

    private fun openDestinationDetail(destination: Destination) {
        val intent = Intent(this, DestinationDetailActivity::class.java).apply {
            putExtra("DESTINATION_ID", destination.id)
            putExtra("DESTINATION_NAME", destination.name)
            putExtra("DESTINATION_LOCATION", destination.location)
            putExtra("DESTINATION_PRICE", destination.price)
            putExtra("DESTINATION_RATING", destination.rating)
            putExtra("DESTINATION_IMAGE", destination.imageResource)
            putExtra("DESTINATION_DESC", destination.description)
        }
        startActivity(intent)
    }
}