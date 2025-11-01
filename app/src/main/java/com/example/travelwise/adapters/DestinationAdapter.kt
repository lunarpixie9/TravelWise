package com.example.travelwise.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelwise.databinding.ItemDestinationCardBinding
import com.example.travelwise.models.Destination

class DestinationAdapter(
    private val onItemClick: (Destination) -> Unit
) : ListAdapter<Destination, DestinationAdapter.DestinationViewHolder>(DestinationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val binding = ItemDestinationCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DestinationViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DestinationViewHolder(
        private val binding: ItemDestinationCardBinding,
        private val onItemClick: (Destination) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(destination: Destination) {
            binding.apply {
                // Set image
                ivDestination.setImageResource(destination.imageResource)

                // Set text content
                tvDestinationName.text = destination.name
                tvLocation.text = destination.location
                tvPrice.text = "$${destination.price.toInt()}"
                tvRating.text = destination.rating.toString()

                // Card click listener
                root.setOnClickListener {
                    onItemClick(destination)
                }

                // Action button listeners
                btnCamera.setOnClickListener {
                    // TODO: Handle camera action
                }

                btnNavigation.setOnClickListener {
                    // TODO: Handle navigation action
                }

                btnFavorite.setOnClickListener {
                    // TODO: Handle favorite action
                    // Toggle favorite icon here
                }

                btnCalendar.setOnClickListener {
                    // TODO: Handle calendar action
                }

                btnUser.setOnClickListener {
                    // TODO: Handle user action
                }
            }
        }
    }

    private class DestinationDiffCallback : DiffUtil.ItemCallback<Destination>() {
        override fun areItemsTheSame(oldItem: Destination, newItem: Destination): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Destination, newItem: Destination): Boolean {
            return oldItem == newItem
        }
    }
}