package com.example.filmcatalog.view_model

import androidx.lifecycle.*
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.User
import com.example.domain.auth.use_case.AuthUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthViewModel(private val authUseCase: AuthUseCase) : BaseViewModel() {

    private val _result: MutableLiveData<User> = MutableLiveData()
    val result: LiveData<User> = _result

    fun auth(username: String, password: String) {

        viewModelScope.launch {

            authUseCase.auth(username, password).collect {

                when(it) {
                    is Resource.Loading -> _loading.postValue(true)

                    is Resource.Success -> _result.postValue(it.data!!)

                    is Resource.Error -> _error.postValue(it.message!!)
                }
            }
        }
    }
}