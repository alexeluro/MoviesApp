package com.inspiredcoda.moviesapp.presentation.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.inspiredcoda.moviesapp.R
import com.inspiredcoda.moviesapp.domain.Movie

//@ExperimentalMaterialApi
//@Composable
//fun ImageLoader(
//    movie: Movie,
//    modifier: Modifier
//) {
//    val imageUrl = "https://www.themoviedb.org/t/p/w220_and_h330_face${movie.posterUrl}"
//    Log.d(TAG, "ImageLoader: $imageUrl")
//    val scope = rememberCoroutineScope()
//    val imagePainter = rememberImagePainter(
//        data = imageUrl,
//        builder = {
//            placeholder(R.drawable.ic_movies)
//            error(R.drawable.ic_error)
//            crossfade(1000)
//        }
//    )
//    Image(
//        painter = imagePainter,
//        contentDescription = "",
//        modifier = modifier,
//        contentScale = ContentScale.Fit,
//        alignment = Alignment.Center
//    )
//}

@ExperimentalMaterialApi
@Composable
fun ImageLoader(
    modifier: Modifier,
    movie: Movie
) {
    val imageUrl = "https://www.themoviedb.org/t/p/w220_and_h330_face${movie.posterUrl}"
    Log.d(TAG, "ImageLoader: $imageUrl")
    val imagePainter = rememberImagePainter(
        data = imageUrl,
        builder = {
            placeholder(R.drawable.ic_movies)
            error(R.drawable.ic_error)
            crossfade(1000)
        }
    )
    Image(
        painter = imagePainter,
        contentDescription = "",
        modifier = modifier,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    )
}