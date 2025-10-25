package com.example.travelwise.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.travelwise.R
import com.google.android.material.appbar.CollapsingToolbarLayout

class DestinationDetailActivity : AppCompatActivity() {

    private lateinit var ivDestinationImage: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnBook: Button
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)

        // Initialize views
        initViews()

        // Setup toolbar
        setupToolbar()

        // Load data from intent
        loadDestinationData()

        // Setup click listeners
        setupClickListeners()
    }

    private fun initViews() {
        ivDestinationImage = findViewById(R.id.ivDestinationImage)
        tvTitle = findViewById(R.id.tvTitle)
        tvLocation = findViewById(R.id.tvLocation)
        tvPrice = findViewById(R.id.tvPrice)
        tvRating = findViewById(R.id.tvRating)
        tvDescription = findViewById(R.id.tvDescription)
        btnBook = findViewById(R.id.btnBook)
        collapsingToolbar = findViewById(R.id.collapsingToolbar)
        toolbar = findViewById(R.id.toolbar)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun loadDestinationData() {
        val name = intent.getStringExtra("DESTINATION_NAME") ?: "Unknown"
        val location = intent.getStringExtra("DESTINATION_LOCATION") ?: "Unknown"
        val price = intent.getDoubleExtra("DESTINATION_PRICE", 0.0)
        val rating = intent.getDoubleExtra("DESTINATION_RATING", 0.0)
        val imageUrl = intent.getStringExtra("DESTINATION_IMAGE") ?: ""
        val description = intent.getStringExtra("DESTINATION_DESC") ?: ""

        // Set data to views
        collapsingToolbar.title = name
        tvTitle.text = name
        tvLocation.text = location
        tvPrice.text = "$$price"
        tvRating.text = rating.toString()
        tvDescription.text = description

        // Load image
        val imageResId = resources.getIdentifier(
            imageUrl,
            "drawable",
            packageName
        )
        if (imageResId != 0) {
            ivDestinationImage.setImageResource(imageResId)
        } else {
            ivDestinationImage.setImageResource(R.drawable.placeholder_destination)
        }
    }

    private fun setupClickListeners() {
        btnBook.setOnClickListener {
            Toast.makeText(
                this,
                "Booking feature coming soon!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}