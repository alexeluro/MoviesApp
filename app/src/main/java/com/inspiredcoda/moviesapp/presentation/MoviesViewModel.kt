package com.inspiredcoda.moviesapp.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspiredcoda.moviesapp.R
import com.inspiredcoda.moviesapp.domain.Movie
import com.inspiredcoda.moviesapp.domain.repository.MovieRepository
import com.inspiredcoda.moviesapp.domain.repository.MovieRepository.NetworkEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

//    sealed interface UIState {
//        object isLoading : UIState
//        data class Success(val data: List<Movie>) : UIState
//        data class Failure(val message: String) : UIState
//    }

    data class MovieState(
        val isLoading: Boolean = false,
        val data: List<Movie>? = null,
        val message: String? = null
    )


    var uiState by mutableStateOf(MovieState())
        private set

    var selectedMovie by mutableStateOf(
        Movie(
            title = "Movie Title",
            description = "Movie description",
            rating = 8.0,
            poster = R.drawable.ic_launcher_foreground,
            posterUrl = ""
        )
    )

    fun fetchMovies() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true
            )
            when (val response = repository.getPopularMovies()) {
                is NetworkEvents.Success -> {
                    Log.d(TAG, "FetchMovies size: ${response.movies.size}")
                    uiState = uiState.copy(
                        isLoading = false,
                        data = response.movies,
                        message = null
                    )
                }

                is NetworkEvents.Failure -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        data = null,
                        message = response.message
                    )
                }
            }
        }
    }

    init {
        fetchMovies()
    }

}