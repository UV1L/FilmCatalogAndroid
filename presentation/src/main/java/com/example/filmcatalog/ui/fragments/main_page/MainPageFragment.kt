package com.example.filmcatalog.ui.fragments.main_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
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
    private lateinit var _appBarBinding: AppBarMainPageBinding
    private val appBarBinding
        get() = _appBarBinding
    private lateinit var _navHeaderBinding: NavHeaderMainBinding
    private val navHeaderBinding
        get() = _navHeaderBinding

    private val viewModel by viewModels<MainPageViewModel>()

    override fun provideFragmentManager() = parentFragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        _appBarBinding = AppBarMainPageBinding.inflate(inflater, container, false)
        _navHeaderBinding = NavHeaderMainBinding.inflate(inflater, container, false)
        appBarBinding.user = (activity as MainActivity).currentSessionUser
        navHeaderBinding.user = (activity as MainActivity).currentSessionUser
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(context, (activity?.application as BaseApplication).getToken(), Toast.LENGTH_SHORT).show()
    }
}