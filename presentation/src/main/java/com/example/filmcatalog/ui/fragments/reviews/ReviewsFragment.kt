package com.example.filmcatalog.ui.fragments.reviews

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Id
import com.example.domain.auth.entities.Review
import com.example.domain.auth.entities.User
import com.example.domain.auth.use_case.AddReviewUseCaseImpl
import com.example.domain.auth.use_case.GetReviewsUseCaseImpl
import com.example.filmcatalog.BaseApplication
import com.example.filmcatalog.dagger.GlideApp
import com.example.filmcatalog.databinding.FragmentReviewsBinding
import com.example.filmcatalog.ui.base.BaseFragment
import com.example.filmcatalog.ui.fragments.main_page.FilmListFragmentDirections
import com.example.filmcatalog.ui.fragments.review_dialog.OnSendListener
import com.example.filmcatalog.ui.fragments.reviews.recycler_view.ReviewsRecyclerAdapter
import com.example.filmcatalog.utils.Const
import com.example.filmcatalog.view_model.ReviewsViewModel
import com.example.utils.ToastUtils
import javax.inject.Inject

class ReviewsFragment : BaseFragment(), OnSendListener {

    private lateinit var _binding: FragmentReviewsBinding
    private val binding
        get() = _binding

    private lateinit var adapter: ReviewsRecyclerAdapter
    private val film by lazy { getFilmFromArgs() }

    private lateinit var viewModel: ReviewsViewModel

    @Inject
    lateinit var getReviewsUseCase: GetReviewsUseCaseImpl

    @Inject
    lateinit var addReviewUseCase: AddReviewUseCaseImpl

    override fun provideFragmentManager(): FragmentManager = parentFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as BaseApplication).applicationComponent.inject(this)

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ReviewsViewModel(
                    getReviewsUseCase,
                    addReviewUseCase
                ) as T
            }
        })[ReviewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReviewsBinding.inflate(inflater, container, false)

        adapter = ReviewsRecyclerAdapter.getInstance(requireActivity().application)
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reviewsRecyclerView.adapter = adapter
        binding.reviewsRecyclerView.layoutManager = LinearLayoutManager(context)
        setUpFilmView()
        setUpFab()
        viewModel.getReviews(film)
        observeLivedata()
    }

    private fun setUpFilmView() {

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

    private fun setUpFab() {

        binding.reviewsReviewBtn.setOnClickListener {

            val userId = (activity?.application as BaseApplication).getCurrentUserId()

            adapter.currentList.forEach {

                if (it.user.userId == userId) {
                    val alertDialog = AlertDialog.Builder(requireContext())
                        .setMessage("Вы уже оставляли ревью на этот фильм. Хотите его изменить?")
                        .setPositiveButton("Да") { _, _ ->
                            val action =
                                ReviewsFragmentDirections.actionReviewsFragmentToReviewDialogFragment(
                                    this
                                )
                            findNavController().navigate(action)
                        }
                        .setNegativeButton("Нет") { _, _ -> }
                        .create()
                    alertDialog.show()
                    return@setOnClickListener
                }
            }
            val action = ReviewsFragmentDirections.actionReviewsFragmentToReviewDialogFragment(this)
            findNavController().navigate(action)
        }
    }

    private fun getFilmFromArgs(): Film {

        return arguments?.getSerializable("film") as Film
    }

    private fun observeLivedata() {

        viewModel.error.observe(viewLifecycleOwner) {

            hideLoading()
            showError(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {

            showLoading()
        }

        viewModel.reviews.observe(viewLifecycleOwner) {

            hideLoading()
            adapter.addReviewsList(it)
        }

        viewModel.success.observe(viewLifecycleOwner) {

            ToastUtils.showShortMessage(context, "Рецензия успешно отправлена!")
            findNavController().popBackStack()
            viewModel.getReviews(film)
        }
    }

    override fun onSend(reviewText: String, rating: Float) {

        val userId = (activity?.application as BaseApplication).getCurrentUserId()
        val username = (activity?.application as BaseApplication).getCurrentUsername()
        viewModel.addReview(Review(Id(film.id), reviewText, User(userId, username!!, null), rating))
        val action = FilmListFragmentDirections.actionFilmListFragmentToReviewsFragment(film)
        findNavController().navigate(action)
    }
}