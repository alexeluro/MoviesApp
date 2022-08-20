package com.inspiredcoda.moviesapp.presentation.ui

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inspiredcoda.moviesapp.domain.Movie
import com.inspiredcoda.moviesapp.presentation.MoviesViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController, moviesViewModel: MoviesViewModel) {
//    moviesViewModel.fetchMovies()
    val skipHalfExpanded = remember { mutableStateOf(true) }
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded.value
    )
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheet(
                bottomSheetState,
                moviesViewModel.selectedMovie,
                navController
            )
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(
            topEnd = 10.dp,
            topStart = 10.dp,
        ),
        sheetElevation = 10.dp
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.padding(horizontal = 16.dp),
            topBar = { }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(horizontal = paddingValues.calculateLeftPadding(LayoutDirection.Ltr)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(1f),
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Hi, Alex",
                        style = MaterialTheme.typography.h3.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.onBackground
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Choose a movie below to watch",
                        style = MaterialTheme.typography.h3.copy(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(5.dp),
                    ) {
                        items(items = moviesViewModel.uiState.data ?: emptyList()) { item: Movie ->
                            Box(
                                modifier = Modifier
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
                                ImageLoader(movie = item, modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = Color.Transparent
                                    )
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable {
                                        Log.d(ContentValues.TAG, "HomeScreen: ${item.title}")
                                        moviesViewModel.selectedMovie = item
                                        scope.launch {
                                            if (bottomSheetState.currentValue == ModalBottomSheetValue.Hidden)
                                                bottomSheetState.snapTo(ModalBottomSheetValue.Expanded)
                                            else bottomSheetState.hide()
                                        }
                                    })
                            }
                        }
                    }

                }

                if (moviesViewModel.uiState.isLoading) {
                    CircularProgressIndicator(
                        color = Color.Blue,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomSheet(state: ModalBottomSheetState, movie: Movie, navController: NavHostController) {
    val descText =
        "Lorem ipsum dolor sit amet, conse ctetur adipiscing elit. A quisque lac inia pellentesque auctor bibendum."

    when (state.currentValue) {
        ModalBottomSheetValue.HalfExpanded,
        ModalBottomSheetValue.Expanded -> MoviePreview(navController, movie = movie)
        ModalBottomSheetValue.Hidden -> Spacer(modifier = Modifier.defaultMinSize(minHeight = 1.dp))
    }
}

@Composable
@Preview
fun previewComposable() {

}