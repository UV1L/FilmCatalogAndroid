package com.example.filmcatalog.ui.fragments.reviews.recycler_view

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.auth.entities.Review
import com.example.filmcatalog.BaseApplication
import com.example.filmcatalog.databinding.ReviewLayoutBinding
import com.example.filmcatalog.ui.fragments.main_page.recycler_view.FilmListRecyclerAdapter

class ReviewsRecyclerAdapter :
    ListAdapter<Review, ReviewsRecyclerAdapter.ReviewsViewHolder>(DiffCallback()) {

    companion object {

        lateinit var app: Application

        fun getInstance(
            application: Application
        ): ReviewsRecyclerAdapter {

            app = application

            return ReviewsRecyclerAdapter()
        }
    }

    inner class ReviewsViewHolder(val binding: ReviewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class DiffCallback : DiffUtil.ItemCallback<Review>() {

        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {

        val binding =
            ReviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.binding.reviewText.text = currentList[position].reviewText
        holder.binding.reviewUsername.text = currentList[position].user.username
        holder.binding.reviewRating.rating = currentList[position].rating
    }

    override fun getItemCount(): Int = currentList.size

    fun addReviewsList(list: List<Review>) {

        submitList(list)
    }
}