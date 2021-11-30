package com.example.filmcatalog.ui.fragments.main_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.filmcatalog.BaseApplication
import com.example.filmcatalog.MainActivity
import com.example.filmcatalog.view_model.MainPageViewModel
import com.example.filmcatalog.R
import com.example.filmcatalog.databinding.AppBarMainPageBinding
import com.example.filmcatalog.databinding.FragmentMainPageBinding
import com.example.filmcatalog.databinding.NavHeaderMainBinding
import com.example.filmcatalog.ui.base.BaseFragment

class MainPageFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainPageFragment()
    }

    private lateinit var _binding: FragmentMainPageBinding
    private val binding
        get() = _binding

    private val viewModel by viewModels<MainPageViewModel>()

    override fun provideFragmentManager() = parentFragmentManager

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
//        binding.appBarMain.
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
}