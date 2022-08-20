package com.inspiredcoda.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inspiredcoda.moviesapp.domain.Movie
import com.inspiredcoda.moviesapp.presentation.MoviesViewModel
import com.inspiredcoda.moviesapp.presentation.ui.DetailsScreen
import com.inspiredcoda.moviesapp.presentation.ui.HomeScreen
import com.inspiredcoda.moviesapp.presentation.ui.theme.MoviesAppTheme
import com.inspiredcoda.moviesapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val moviesViewModel by viewModels<MoviesViewModel>()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    setupNavigation(navController = navController, moviesViewModel)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun setupNavigation(navController: NavHostController, moviesViewModel: MoviesViewModel) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(Routes.HomeScreen.route) {
            HomeScreen(navController, moviesViewModel)
        }
        composable(Routes.MovieDetailsScreen.route) {
            DetailsScreen(navController, moviesViewModel.selectedMovie)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesAppTheme {
//        Root()
    }
}