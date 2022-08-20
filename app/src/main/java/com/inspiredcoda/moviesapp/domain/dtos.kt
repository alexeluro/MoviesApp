package com.inspiredcoda.moviesapp.domain

import com.inspiredcoda.moviesapp.domain.remote.PopularMoviesResponse

fun PopularMoviesResponse.MovieResponseData.toMovie(): Movie {
    return Movie(
        title = title ?: "",
        description = overview ?: "",
        rating = this.rating ?: 0.0,
        posterUrl = this.posterUrl ?: ""
    )
}