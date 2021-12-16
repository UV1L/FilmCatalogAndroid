package com.example.filmcatalog.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.Review
import com.example.domain.auth.use_case.ReviewsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReviewsViewModel(private val getReviewsUseCase: ReviewsUseCase.GetReviewsUseCase,
                       private val addReviewUseCase: ReviewsUseCase.AddReviewUseCase) : BaseViewModel() {

    private val _reviews: MutableLiveData<List<Review>> = MutableLiveData()
    val reviews: LiveData<List<Review>> = _reviews

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> = _success

    fun getReviews(film: Film) {

        viewModelScope.launch {

            getReviewsUseCase.getReviews(film).collect {

                when(it) {
                    is Resource.Loading -> _loading.postValue(true)

                    is Resource.Success -> _reviews.postValue(it.data!!)

                    is Resource.Error -> _error.postValue(it.message!!)
                }
            }
        }
    }

    fun addReview(review: Review) {

        viewModelScope.launch {

            addReviewUseCase.addReview(review).collect {

                when(it) {

                    is Resource.Success -> _success.postValue(true)

                    is Resource.Error -> _error.postValue(it.message!!)
                }
            }
        }
    }
}