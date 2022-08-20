package com.inspiredcoda.moviesapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inspiredcoda.moviesapp.domain.Movie

@ExperimentalMaterialApi
@Composable
fun DetailsScreen(navController: NavHostController, movie: Movie) {
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.padding(horizontal = 0.dp),
        topBar = { AppBar(navController = navController, title = "Details") }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .fillMaxSize()
                .padding(
                    horizontal = paddingValues.calculateLeftPadding(LayoutDirection.Ltr) + 16.dp
                ),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
            ) {
                ImageLoader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    movie = movie,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Divider(
                modifier = Modifier
                    .background(color = Color.Black)
                    .height(1.dp),
                color = MaterialTheme.colors.onBackground,
                thickness = 1.dp
            )
            Text(
                text = movie.description,
                style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.onBackground)
            )


        }
    }
}
