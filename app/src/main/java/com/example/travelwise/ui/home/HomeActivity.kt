package com.example.travelwise.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelwise.adapters.DestinationAdapter
import com.example.travelwise.databinding.ActivityHomeBinding
import com.example.travelwise.models.Destination
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var destinationAdapter: DestinationAdapter
    private val destinations = mutableListOf<Destination>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
        observeViewModel()

        // Load sample data
        loadSampleData()
    }

    private fun setupRecyclerView() {
        // Initialize adapter with click listener
        destinationAdapter = DestinationAdapter { destination ->
            openDestinationDetail(destination)
        }

        // Attach to RecyclerView
        binding.rvDestinations.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = destinationAdapter
        }

        // Optionally set initial data (empty at start)
        destinationAdapter.submitList(destinations)
    }

    private fun openDestinationDetail(destination: Destination) {
        // TODO: Navigate to destination detail
        // val intent = Intent(this, DestinationDetailActivity::class.java)
        // intent.putExtra("destination_id", destination.id)
        // startActivity(intent)
    }

    private fun setupClickListeners() {
        // Category clicks
        binding.categoryHotels.setOnClickListener {
            // TODO: Navigate to hotels category
        }

        binding.categoryFlights.setOnClickListener {
            // TODO: Navigate to flights category
        }

        binding.categoryCars.setOnClickListener {
            // TODO: Navigate to cars category
        }

        binding.categoryMeal.setOnClickListener {
            // TODO: Navigate to meal category
        }

        // Filter chips
        binding.chipBestOffers.setOnClickListener {
            viewModel.loadDestinations("Best Offers")
        }

        binding.chipTrending.setOnClickListener {
            viewModel.loadDestinations("Trending")
        }

        binding.chipFeatured.setOnClickListener {
            viewModel.loadDestinations("Featured")
        }

        binding.chipTopVisit.setOnClickListener {
            viewModel.loadDestinations("Top Visit")
        }

        // Menu button
        binding.btnMenu.setOnClickListener {
            // TODO: Open navigation drawer or menu
        }

        // Filter button
        binding.btnFilter.setOnClickListener {
            // TODO: Show filter bottom sheet
        }
    }

    private fun observeViewModel() {
        // Observe destination list updates
        viewModel.destinations.observe(this) { newDestinations ->
            destinationAdapter.submitList(newDestinations)
        }

        // Observe current location
        viewModel.location.observe(this) { location ->
            binding.tvLocation.text = location
        }
    }

    private fun loadSampleData() {
        lifecycleScope.launch {
            // Load initial data
            viewModel.loadDestinations("Best Offers")
        }
    }
}
