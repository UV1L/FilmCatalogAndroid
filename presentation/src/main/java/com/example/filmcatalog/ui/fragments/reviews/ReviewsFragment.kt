package com.example.filmcatalog.ui.fragments.reviews

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.domain.auth.entities.Film
import com.example.filmcatalog.dagger.GlideApp
import com.example.filmcatalog.databinding.FragmentReviewsBinding
import com.example.filmcatalog.ui.base.BaseFragment
import com.example.filmcatalog.ui.fragments.reviews.recycler_view.ReviewsRecyclerAdapter
import com.example.filmcatalog.utils.Const

class ReviewsFragment : BaseFragment() {

    private lateinit var _binding: FragmentReviewsBinding
    private val binding
        get() = _binding

    private lateinit var adapter: ReviewsRecyclerAdapter
    private val film by lazy { getFilmFromArgs() }

    override fun provideFragmentManager(): FragmentManager = parentFragmentManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReviewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = mutableListOf(
            "Отличный фильм для всей семьи! 10 из 10",
            "Отличный фильм для всей семьи! 9 из 10",
            "Отличный фильм для всей семьи! 8 из 10",
            "Отличный фильм для всей семьи! 7 из 10",
            "Отличный фильм для всей семьи! 6 из 10",
            "Отличный фильм для всей семьи! 5 из 10"
        )

        adapter = ReviewsRecyclerAdapter(list)
        binding.reviewsRecyclerView.adapter = adapter
        binding.reviewsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.reviewsFilm.filmTitle.text = film.title
        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        GlideApp
            .with(requireContext())
            .load(Const.POSTER_BASE_URL.plus(film.posterPath))
            .placeholder(circularProgressDrawable)
            .into(binding.reviewsFilm.filmPoster)
        binding.reviewsFilm.filmRating.text = film.rating.toString()
        when {
            film.rating > 8 -> binding.reviewsFilm.filmRating.setTextColor(Color.parseColor("#88FB60"))
            film.rating > 7 -> binding.reviewsFilm.filmRating.setTextColor(Color.parseColor("#61954E"))
            film.rating > 5 -> binding.reviewsFilm.filmRating.setTextColor(Color.parseColor("#CCC660"))
            film.rating > 4 -> binding.reviewsFilm.filmRating.setTextColor(Color.parseColor("#C15635"))
        }
        binding.reviewsFilm.filmOverview.text = film.overview
        binding.reviewsFilm.filmReviewBtn.visibility = View.GONE
        binding.reviewsFilm.filmFullTitleBtn.visibility = View.GONE
        binding.reviewsFilm.filmFavourite.visibility = View.GONE
    }

    private fun getFilmFromArgs(): Film {

        return arguments?.getSerializable("film") as Film
    }
}