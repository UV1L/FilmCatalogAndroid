package com.example.filmcatalog.ui.fragments.main_page.recycler_view

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.User
import com.example.filmcatalog.BaseApplication
import com.example.filmcatalog.dagger.GlideApp
import com.example.filmcatalog.databinding.FilmLayoutBinding
import com.example.filmcatalog.utils.Const.POSTER_BASE_URL
import com.example.filmcatalog.utils.Const.USERNAME_PREF_UTILS
import com.example.filmcatalog.utils.PrefUtils
import javax.inject.Inject

class FilmListRecyclerAdapter(
    private val itemSelectedListener: ItemSelectedListener
) : ListAdapter<Film, FilmListRecyclerAdapter.FilmViewHolder>(DiffCallback()) {

    companion object {

        lateinit var app: Application

        fun getInstance(application: Application,
                        itemSelectedListener: ItemSelectedListener
        ): FilmListRecyclerAdapter {

            app = application

            return FilmListRecyclerAdapter(itemSelectedListener)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Film>() {

        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean =
            oldItem == newItem
    }

    interface ItemSelectedListener {
        fun onItemSelected(item: Film)
        fun onViewAllClickListener(item: Film)
        fun onReviewClickListener(item: Film)
    }

    inner class FilmViewHolder(val binding: FilmLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.filmFavourite.setOnClickListener {

                it.isSelected = !it.isSelected
                itemSelectedListener.onItemSelected(currentList[adapterPosition])
            }
            binding.filmFullTitleBtn.setOnClickListener {

                itemSelectedListener.onViewAllClickListener(currentList[adapterPosition])
            }
            binding.filmReviewBtn.setOnClickListener {

                itemSelectedListener.onReviewClickListener(currentList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {

        val binding = FilmLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {

        val prefs = PrefUtils.create(app.applicationContext)
        val username = prefs.getString(USERNAME_PREF_UTILS, "")!!

        with(currentList[position]) {

            holder.binding.filmTitle.text = this.title
            holder.binding.filmOverview.text = this.overview
            holder.binding.filmRating.text = this.rating.toString()
            holder.binding.filmId = this.id
            if (this.likedBy.contains(User(username))) {
                holder.binding.filmFavourite.isSelected = true
            }
            when {
                this.rating > 8 -> holder.binding.filmRating.setTextColor(Color.parseColor("#88FB60"))
                this.rating > 7 -> holder.binding.filmRating.setTextColor(Color.parseColor("#61954E"))
                this.rating > 5 -> holder.binding.filmRating.setTextColor(Color.parseColor("#CCC660"))
                this.rating > 4 -> holder.binding.filmRating.setTextColor(Color.parseColor("#C15635"))
            }

            val circularProgressDrawable = CircularProgressDrawable(holder.binding.root.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            GlideApp
                .with(holder.binding.filmPoster.context)
                .load(POSTER_BASE_URL.plus(this.posterPath))
                .placeholder(circularProgressDrawable)
                .into(holder.binding.filmPoster)
        }
    }

    override fun getItemCount(): Int = currentList.size

    fun addFilmsList(list: List<Film>) {

        submitList(list)
    }

    fun addFilm(film: Film) {

        submitList(currentList.plus(film))
    }
}