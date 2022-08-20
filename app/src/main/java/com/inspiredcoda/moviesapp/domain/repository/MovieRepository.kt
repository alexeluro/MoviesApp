package com.inspiredcoda.moviesapp.domain.repository

import com.inspiredcoda.moviesapp.R
import com.inspiredcoda.moviesapp.domain.Movie
import com.inspiredcoda.moviesapp.domain.remote.ApiService
import com.inspiredcoda.moviesapp.domain.toMovie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) : BaseRepository() {

    sealed interface NetworkEvents {
        data class Success(val movies: List<Movie>) : NetworkEvents
        data class Failure(val message: String) : NetworkEvents
    }

    suspend fun getPopularMovies(): NetworkEvents {
        return try {
            val response = makeApiRequest { apiService.getPopularMovies() }
            if (response.status) {
                val movies = response.data?.results?.map {
                    it.toMovie()
                }
                NetworkEvents.Success(movies ?: emptyList())
            } else {
                NetworkEvents.Failure(response.message)
            }
        } catch (ex: Exception) {
            NetworkEvents.Failure(ex.message ?: "Something went wrong")
        }
    }

    private fun dummyData(): NetworkEvents {
        return NetworkEvents.Success(
            listOf(
                Movie(
                    title = "Winter Soldier",
                    description = "A story about a man who was mind controlled and now has to pay for the sins he commited as the Winter Soldier.",
                    rating = 5.0,
                    poster = R.drawable.ic_launcher_foreground,
                    posterUrl = ""
                ),
                Movie(
                    title = "Iron-man",
                    description = "A man with an iron suit",
                    rating = 5.0,
                    poster = R.drawable.ic_launcher_foreground,
                    posterUrl = ""
                ),
                Movie(
                    title = "Thor: Love and Thunder",
                    description = "Thor embarks on a journey with his friends; members of the Guardians of the Galaxy on an amazing adventure.",
                    rating = 5.0,
                    poster = R.drawable.ic_launcher_foreground,
                    posterUrl = ""
                ),
            )
        )
    }

}