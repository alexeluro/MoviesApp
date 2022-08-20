package com.inspiredcoda.moviesapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inspiredcoda.moviesapp.domain.Movie
import com.inspiredcoda.moviesapp.util.Routes

@ExperimentalMaterialApi
@Composable
fun MoviePreview(navController: NavController, movie: Movie) {
    Row(
        modifier = Modifier
            .defaultMinSize(minHeight = 1.dp)
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(
                    topStart = CornerSize(10.dp),
                    topEnd = CornerSize(10.dp),
                    bottomEnd = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp)
                ),
                color = MaterialTheme.colors.background
            )
            .padding(10.dp)
            .clickable {
                navController.navigate(
                    Routes.MovieDetailsScreen.route,
                )
            },
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.Black),
        ) {
            ImageLoader(
                modifier = Modifier
                    .fillMaxSize()
                    .height(180.dp)
                    .width(200.dp)
                    .background(
                        color = Color.Transparent
                    )
                    .clip(RoundedCornerShape(10.dp)),
                movie = movie
            )
        }
        Column(
            modifier = Modifier
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = movie.description,
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onBackground),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
            Divider(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .height(1.dp),
                color = MaterialTheme.colors.onBackground,
                thickness = 0.5.dp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Info icon",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "Read more...",
                        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onBackground),
                    )
                }
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Info icon",
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}