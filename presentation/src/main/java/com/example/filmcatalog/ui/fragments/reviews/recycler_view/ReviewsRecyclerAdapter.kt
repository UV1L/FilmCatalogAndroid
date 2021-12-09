package com.example.filmcatalog.ui.fragments.reviews.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.auth.entities.Film
import com.example.filmcatalog.databinding.FragmentReviewsBinding
import com.example.filmcatalog.databinding.ReviewLayoutBinding

class ReviewsRecyclerAdapter(private val reviews: MutableList<String>) : RecyclerView.Adapter<ReviewsRecyclerAdapter.ReviewsViewHolder>() {

    inner class ReviewsViewHolder(val binding: ReviewLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {

        val binding = ReviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.binding.reviewText.text = reviews[position]
    }

    override fun getItemCount(): Int = reviews.size
}