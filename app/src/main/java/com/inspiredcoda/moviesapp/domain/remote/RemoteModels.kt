package com.inspiredcoda.moviesapp.domain.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseWrapper<T>(
    @SerialName("status") val status: Boolean,
    @SerialName("message") val message: String,
    @SerialName("data") val data: T?,
)

@Serializable
data class PopularMoviesResponse(
    @SerialName("page") val page: Int?,
    @SerialName("results") val results: List<MovieResponseData>
) {

    @Serializable
    data class MovieResponseData(
        @SerialName("id") val id: Long?,
        @SerialName("title") val title: String?,
        @SerialName("vote_average") val rating: Double?,
        @SerialName("poster_path") val posterUrl: String?,
        @SerialName("overview") val overview: String?,
    )

}
