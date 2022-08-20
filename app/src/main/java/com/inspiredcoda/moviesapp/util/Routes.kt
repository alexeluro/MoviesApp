package com.inspiredcoda.moviesapp.util

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home_screen")
    object MovieDetailsScreen : Routes("movie_details_screen")
}
