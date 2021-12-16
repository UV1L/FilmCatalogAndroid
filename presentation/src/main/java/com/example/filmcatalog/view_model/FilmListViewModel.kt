package com.example.filmcatalog.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.use_case.FavouritesUseCase
import com.example.domain.auth.use_case.FilmsUseCase
import com.example.domain.auth.use_case.FilmsUseCaseImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FilmListViewModel(private val filmsUseCase: FilmsUseCaseImpl,
                        private val addFavouritesUseCase: FavouritesUseCase.AddFavouritesUseCase,
                        private val getFavouritesUseCase: FavouritesUseCase.GetFavouritesUseCase
) : BaseViewModel() {

    private val _films: MutableLiveData<List<Film>> = MutableLiveData()
    val films: LiveData<List<Film>> = _films

    fun getAllFilms(token: String) {

        viewModelScope.launch {

            filmsUseCase.getFilms(token).collect {

                when(it) {
                    is Resource.Loading -> _loading.postValue(true)

                    is Resource.Success -> _films.postValue(it.data!!)

                    is Resource.Error -> _error.postValue(it.message!!)
                }
            }
        }
    }

    fun addFilmToFavourites(token: String, filmId: Int) {

        viewModelScope.launch {

            addFavouritesUseCase.addFavourite(token, filmId).collect {

                when(it) {
                    is Resource.Error -> _error.postValue(it.message!!)
                }
            }
        }
    }

    fun getFavouriteFilms(token: String) {

        viewModelScope.launch {

            getFavouritesUseCase.getFavourites(token).collect {

                when(it) {
                    is Resource.Loading -> _loading.postValue(true)

                    is Resource.Success -> _films.postValue(it.data!!.toList())

                    is Resource.Error -> _error.postValue(it.message!!)
                }
            }
        }
    }
}