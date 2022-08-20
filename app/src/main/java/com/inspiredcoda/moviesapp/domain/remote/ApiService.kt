package com.inspiredcoda.moviesapp.domain.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = PAGE,
    ): Response<PopularMoviesResponse>


    companion object {
        const val API_KEY = "b95ac834ba4ee2586f6553d9b2e898f7"
        const val LANGUAGE = "en-US"
        const val PAGE = 1
    }

}