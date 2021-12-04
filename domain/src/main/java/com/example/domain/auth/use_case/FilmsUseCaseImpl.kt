package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.repo.FilmsRepository
import kotlinx.coroutines.flow.Flow

class FilmsUseCaseImpl(private val filmsRepository: FilmsRepository) : FilmsUseCase {

    override suspend fun getFilms(token: String): Flow<Resource<List<Film>>> {

        return filmsRepository.getFilms(token)
    }
}