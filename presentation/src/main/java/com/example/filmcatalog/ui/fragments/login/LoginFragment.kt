package com.example.filmcatalog.ui.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.domain.auth.entities.User
import com.example.filmcatalog.AuthObserver
import com.example.filmcatalog.BaseApplication
import com.example.filmcatalog.MainActivity
import com.example.filmcatalog.R
import com.example.filmcatalog.databinding.FragmentLoginBinding
import com.example.filmcatalog.ui.base.BaseFragment
import com.example.filmcatalog.view_model.AuthViewModel
import java.util.ArrayList

class LoginFragment() : BaseFragment() {

    private lateinit var authViewModel: AuthViewModel

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var authObservers: List<AuthObserver>

    private val username: String
        get() = binding.loginFragmentLoginEditTxt.text.toString()

    private val password: String
        get() = binding.loginFragmentPasswordEditTxt.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authUseCase = (requireActivity().application as BaseApplication)
            .applicationComponent.injectAuthUseCase()

        authViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(authUseCase) as T
            }
        }).get(AuthViewModel::class.java)

        authObservers = listOf(activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLoginBtnOnClickListener()
        setRegisterBtnOnClickListener()
        observeLivedata()
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    private fun setLoginBtnOnClickListener() {

        binding.loginFragmentLoginBtn.setOnClickListener {

            if (username != "" && password != "")
                authViewModel.auth(username, password)
            else
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRegisterBtnOnClickListener() {

        binding.loginFragmentRegisterTxt.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun observeLivedata() {

        authViewModel.error.observe(viewLifecycleOwner) {

            hideLoading()
            showError(it)
        }

        authViewModel.loading.observe(viewLifecycleOwner) {

            showLoading()
        }

        authViewModel.token.observe(viewLifecycleOwner) {

            hideLoading()
            (activity?.application as BaseApplication).saveTokenToSharedPreferences(it)
            authObservers.forEach { it.setCurrentSessionUser(User(username)) }
            findNavController().navigate(R.id.action_loginFragment_to_mainPageFragment)
        }
    }

    override fun provideFragmentManager(): FragmentManager = parentFragmentManager
}
