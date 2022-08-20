package com.inspiredcoda.moviesapp.domain.remote

import com.inspiredcoda.moviesapp.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = PAGE,
    ): Response<PopularMoviesResponse>


    companion object {
        const val LANGUAGE = "en-US"
        const val PAGE = 1
    }

}