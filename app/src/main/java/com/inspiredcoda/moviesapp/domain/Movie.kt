package com.inspiredcoda.moviesapp.domain

import android.graphics.Bitmap
import com.inspiredcoda.moviesapp.R

data class Movie(
    val title: String,
    val description: String,
    val rating: Double,
    val poster: Int = R.drawable.ic_launcher_foreground,
    val posterUrl: String = ""
)
