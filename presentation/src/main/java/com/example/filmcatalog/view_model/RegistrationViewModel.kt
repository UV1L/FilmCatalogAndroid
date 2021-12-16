package com.example.filmcatalog.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.use_case.RegisterUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegistrationViewModel(private val registerUseCase: RegisterUseCase) : BaseViewModel() {

    private val _username: MutableLiveData<String> = MutableLiveData()
    val username: LiveData<String> = _username

    fun register(
        email: String,
        username: String,
        password_1: String,
        password_2: String
    ) {

        viewModelScope.launch {

            registerUseCase.register(email, username, password_1, password_2).collect {

                when(it) {
                    is Resource.Loading -> _loading.postValue(true)

                    is Resource.Success -> _username.postValue(it.data!!)

                    is Resource.Error -> _error.postValue(it.message!!)
                }
            }
        }
    }
}