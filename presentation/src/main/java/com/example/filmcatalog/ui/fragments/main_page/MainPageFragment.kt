package com.example.filmcatalog.ui.fragments.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.auth.entities.User
import com.example.filmcatalog.BaseApplication
import com.example.filmcatalog.MainActivity
import com.example.filmcatalog.view_model.MainPageViewModel
import com.example.filmcatalog.R
import com.example.filmcatalog.databinding.FragmentMainPageBinding
import com.example.filmcatalog.ui.base.BaseFragment
import com.example.filmcatalog.ui.fragments.main_page.recycler_view.FilmListRecyclerAdapter
import com.example.filmcatalog.view_model.AuthViewModel
import com.example.filmcatalog.view_model.RegistrationViewModel

class MainPageFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainPageFragment()
    }

    private lateinit var _binding: FragmentMainPageBinding
    private val binding
        get() = _binding

//    private val viewModel by viewModels<MainPageViewModel>()
    private lateinit var mainPageViewModel: MainPageViewModel

    private val adapter = FilmListRecyclerAdapter(mutableListOf())

    override fun provideFragmentManager() = parentFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filmsUseCase = (requireActivity().application as BaseApplication)
            .applicationComponent.injectFilmsUseCase()

        mainPageViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainPageViewModel(filmsUseCase) as T
            }
        }).get(MainPageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        binding.appBarMain.user = (activity as MainActivity).currentSessionUser
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.navHeaderUsername).text = (activity as MainActivity).currentSessionUser?.username
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavigationOnClickListener()
        observeLivedata()
        mainPageViewModel.getAllFilms((activity?.application as BaseApplication).getToken()!!)
        binding.appBarMain.mainPageRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.appBarMain.mainPageRecyclerView.adapter = adapter
    }

    private fun setNavigationOnClickListener() {

        binding.navView.setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.nav_home -> true
                R.id.nav_favourites -> true
                else -> true
            }
        }
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
}