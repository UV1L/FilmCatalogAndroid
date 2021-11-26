package com.example.filmcatalog.view_model

import androidx.lifecycle.*
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.use_case.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _token: MutableLiveData<String> = MutableLiveData()
    val token: LiveData<String> = _token

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun auth(username: String, password: String) {

        viewModelScope.launch {

            authUseCase.auth(username, password).collect {

                when(it) {
                    is Resource.Loading -> _loading.postValue(true)

                    is Resource.Success -> _token.postValue(it.data!!)

                    is Resource.Error -> _error.postValue(it.message!!)
                }
            }
        }
    }
}