package com.example.filmcatalog.ui.fragments.main_page

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.User
import com.example.domain.auth.use_case.AddFavouritesUseCase
import com.example.domain.auth.use_case.FilmsUseCase
import com.example.domain.auth.use_case.GetFavouritesUseCase
import com.example.filmcatalog.BaseApplication
import com.example.filmcatalog.view_model.MainPageViewModel
import com.example.filmcatalog.R
import com.example.filmcatalog.databinding.FilmLayoutBinding
import com.example.filmcatalog.databinding.FragmentFilmListBinding
import com.example.filmcatalog.ui.base.BaseFragment
import com.example.filmcatalog.ui.fragments.main_page.recycler_view.FilmListRecyclerAdapter
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject

class FilmListFragment : BaseFragment(),
    FilmListRecyclerAdapter.ItemSelectedListener {

    companion object {
        fun newInstance() = FilmListFragment()
    }

    private val SHOW_FAVOURITES_ONLY by lazy { arguments?.getBoolean("showFavourites", false) }
    private lateinit var _binding: FragmentFilmListBinding
    private val binding
        get() = _binding

    private lateinit var _filmLayoutBinding: FilmLayoutBinding
    private val filmLayoutBinding
        get() = _filmLayoutBinding

    private val token: String
        get() = (activity?.application as BaseApplication).getToken()!!

    //    private val viewModel by viewModels<MainPageViewModel>()
    private lateinit var mainPageViewModel: MainPageViewModel

//    private lateinit var prefUtils: PrefUtils

    private lateinit var adapter: FilmListRecyclerAdapter

    @Inject
    lateinit var filmsUseCase: FilmsUseCase

    @Inject
    lateinit var addFavouritesUseCase: AddFavouritesUseCase

    @Inject
    lateinit var getFavouritesUseCase: GetFavouritesUseCase

    override fun provideFragmentManager() = parentFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as BaseApplication).applicationComponent.inject(this)

        mainPageViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainPageViewModel(
                    filmsUseCase,
                    addFavouritesUseCase,
                    getFavouritesUseCase
                ) as T
            }
        }).get(MainPageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmListBinding.inflate(inflater, container, false)
        binding.appBarMain.user =
            User((activity?.application as BaseApplication).getCurrentUsername()!!)

        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.navHeaderUsername).text =
            (activity?.application as BaseApplication).getCurrentUsername()!!
        _filmLayoutBinding =
            FilmLayoutBinding.inflate(LayoutInflater.from(context), container, false)

        adapter = FilmListRecyclerAdapter.getInstance(
            requireActivity().application,
            this
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavigationOnClickListener()
        observeLivedata()
        if (SHOW_FAVOURITES_ONLY!!) {
            binding.navView.setCheckedItem(R.id.nav_favourites)
            mainPageViewModel.getFavouriteFilms(token)
        } else {
            binding.navView.setCheckedItem(R.id.nav_home)
            mainPageViewModel.getAllFilms(token)
        }
        binding.appBarMain.mainPageRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.appBarMain.mainPageRecyclerView.adapter = adapter
        binding.appBarMain.mainPageRecyclerView.addItemDecoration(object:RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.top = 20
            }
        })
    }

    private fun setNavigationOnClickListener() {

        binding.navView.setNavigationItemSelectedListener(

            object : NavigationView.OnNavigationItemSelectedListener {

                override fun onNavigationItemSelected(item: MenuItem): Boolean {

                    when (item.itemId) {
                        R.id.nav_home -> findNavController().navigate(R.id.action_favouritesFragment_to_mainPageFrament)
                        R.id.nav_favourites -> findNavController().navigate(R.id.action_mainPageFragment_to_favouritesFragment)
                        else -> {
                        }
                    }

                    return true
                }
            }
        )
    }

    private fun observeLivedata() {

        mainPageViewModel.error.observe(viewLifecycleOwner) {

            hideLoading()
            showError(it)
        }

        mainPageViewModel.loading.observe(viewLifecycleOwner) {

            showLoading()
        }

        mainPageViewModel.films.observe(viewLifecycleOwner) {

            hideLoading()
            adapter.addFilmsList(it)
        }
    }

    override fun onItemSelected(item: Film) {

        mainPageViewModel.addFilmToFavourites(token, item.id)
    }

    override fun onViewAllClickListener(item: Film) {

        val action =
            FilmListFragmentDirections.filmListFragmentToOverviewDialogFragment(item.overview)
        findNavController().navigate(action)
    }

    override fun onReviewClickListener(item: Film) {

        val action = FilmListFragmentDirections.actionFilmListFragmentToReviewsFragment(item)
        findNavController().navigate(action)
    }
}