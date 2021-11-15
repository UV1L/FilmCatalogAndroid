package com.example.filmcatalog.fragments.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmcatalog.BaseApplication
import com.example.filmcatalog.databinding.FragmentLoginBinding
import com.example.filmcatalog.view_model.AuthViewModel

class LoginFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private var username: String? = null
        get() = binding.loginFragmentLoginEditTxt.text.toString()

    private var password: String? = null
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

        setEditTextListener(binding.loginFragmentLoginEditTxt, binding.loginFragmentPasswordEditTxt)
        setLoginBtnOnClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    private fun setLoginBtnOnClickListener() {

        binding.loginFragmentLoginBtn.setOnClickListener {

            if (username != "" && password != "")
                authViewModel.auth(username!!, password!!)
            else
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setEditTextListener(vararg targetView: EditText) {

        targetView.forEach {
            it.addTextChangedListener(object : TextWatcher {

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        username = ""
                        password = ""
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        username = s.toString()
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }

                })
            }
        }
    }
}