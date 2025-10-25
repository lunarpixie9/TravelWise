package com.example.travelwise.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelwise.R
import com.example.travelwise.databinding.ActivityHomeBinding
import com.example.travelwise.ui.DestinationDetailActivity
import com.example.travelwise.adapters.DestinationAdapter
import com.example.travelwise.models.Destination
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var destinationAdapter: DestinationAdapter
    private val destinations = mutableListOf<Destination>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide action bar
        supportActionBar?.hide()

        // Initialize RecyclerView
        setupRecyclerView()

        // Load sample data
        loadSampleData()
    }

    private fun setupRecyclerView() {
        destinationAdapter = DestinationAdapter(destinations) { destination ->
            openDestinationDetail(destination)
        }

        binding.rvDestinations.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = destinationAdapter
        }
    }

    private fun loadSampleData() {
        lifecycleScope.launch {
            // FIXED: You were reassigning destinations instead of adding to it
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
            putExtra("DESTINATION_IMAGE", destination.imageResource)  // FIXED: removed ": Int"
            putExtra(
                "DESTINATION_DESC",
                destination.description
            )
        }
        startActivity(intent)
    }
}