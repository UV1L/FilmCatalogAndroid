package com.example.filmcatalog.view_model

import androidx.lifecycle.*
import com.example.domain.auth.use_case.AuthUseCase
import kotlinx.coroutines.launch

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _token: MutableLiveData<String> = MutableLiveData()
    val token: LiveData<String> = _token

    fun auth(username: String, password: String) {

        viewModelScope.launch {
            _token.postValue(
                authUseCase.auth(username, password).asLiveData().value
            )
        }
    }
}