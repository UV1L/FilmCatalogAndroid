package com.example.filmcatalog.ui.fragments.main_page.recycler_view

import android.graphics.Color
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.domain.auth.entities.Film
import com.example.filmcatalog.dagger.GlideApp
import com.example.filmcatalog.databinding.FilmLayoutBinding
import com.example.filmcatalog.utils.Const.POSTER_BASE_URL

class FilmListRecyclerAdapter(private val films: MutableList<Film>) : RecyclerView.Adapter<FilmListRecyclerAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(val binding: FilmLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {

        val binding = FilmLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {

        with(films[position]) {

            holder.binding.filmTitle.text = this.title
            holder.binding.filmOverview.text = this.overview
            holder.binding.filmRating.text = this.rating.toString()
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

    override fun getItemCount(): Int = films.size

    fun addFilmsList(list: List<Film>) {

        films.addAll(list)
        notifyDataSetChanged()
    }

    fun addFilm(film: Film) {

        films.add(film)
        notifyDataSetChanged()
    }
}