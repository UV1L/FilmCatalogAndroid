package com.example.filmcatalog.ui.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.domain.auth.entities.User
import com.example.filmcatalog.BaseApplication
import com.example.filmcatalog.MainActivity
import com.example.filmcatalog.R
import com.example.filmcatalog.databinding.FragmentLoginBinding
import com.example.filmcatalog.databinding.FragmentRegistrationBinding
import com.example.filmcatalog.ui.base.BaseFragment
import com.example.filmcatalog.view_model.AuthViewModel
import com.example.filmcatalog.view_model.RegistrationViewModel

class RegistrationFragment() : BaseFragment() {

    private lateinit var registrationViewModel: RegistrationViewModel

    private var _binding: FragmentRegistrationBinding? = null
    private val binding
        get() = _binding!!

    private val email: String
        get() = binding.registrationFragmentEmailEditTxt.text.toString()

    private val username: String
        get() = binding.registrationFragmentLoginEditTxt.text.toString()

    private val password1: String
        get() = binding.registrationFragmentPassword1EditTxt.text.toString()

    private val password2: String
        get() = binding.registrationFragmentPassword2EditTxt.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val registerUseCase = (requireActivity().application as BaseApplication)
            .applicationComponent.injectRegisterUseCase()

        registrationViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RegistrationViewModel(registerUseCase) as T
            }
        }).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRegisterBtnClickListener()
        observeLivedata()
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    private fun observeLivedata() {

        registrationViewModel.error.observe(viewLifecycleOwner) {

            hideLoading()
            showError(it)
        }

        registrationViewModel.loading.observe(viewLifecycleOwner) {

            showLoading()
        }

        registrationViewModel.username.observe(viewLifecycleOwner) {

            hideLoading()
            showMessage("Вы были успешно зарегистрированы!")
            findNavController().popBackStack()
        }
    }

    private fun setRegisterBtnClickListener() {

        binding.registrationFragmentRegisterBtn.setOnClickListener {

            if (email != "" && username != "" && password1 != "" && password2 != "")
                registrationViewModel.register(email, username, password1, password2)
            else
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    override fun provideFragmentManager(): FragmentManager = parentFragmentManager
}