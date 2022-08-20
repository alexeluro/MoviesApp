package com.inspiredcoda.moviesapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AppBar(navController: NavHostController, title: String) {
    TopAppBar(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 0.dp)
            .background(color = MaterialTheme.colors.primary),
        title = { AppBarTitle(title = title) },
        navigationIcon = { NavIcon(navController) },
        elevation = 7.dp,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    )
}

@Composable
private fun AppBarTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onPrimary)
    )

}

@Composable
private fun NavIcon(navController: NavHostController) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape),
        onClick = {
            navController.popBackStack()
        },
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Button"
        )
    }
}