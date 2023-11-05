package com.billion_dollor_company.moviesapp.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.billion_dollor_company.moviesapp.model.getMovies
import com.billion_dollor_company.moviesapp.ui.components.MovieImageRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenLayout(
    navController: NavController,
    movieName: String,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = movieName
                        )
                    },
                    modifier = Modifier
                        .shadow(elevation = 5.dp),
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                    ),
                    navigationIcon = if (navController.previousBackStackEntry != null) {
                        {
                            IconButton(
                                onClick = {
                                    navController.navigateUp()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    } else {
                        {}
                    }
                )
            },
        ) {
            Surface(
                modifier = Modifier
                    .padding(it)
            ) {
                content()
            }
        }
    }
}

@Composable
fun DetailsScreen(
    navController: NavController,
    id: String
) {
    val allMoviesList = getMovies()
    var movie = allMoviesList.find {
        it.id == id
    }

    if (movie == null) movie = allMoviesList[0]

    DetailsScreenLayout(
        navController = navController,
        movieName = movie.title
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.poster)
                    .crossfade(true)
                    .build(),
                contentDescription = "Poster of the movie named: ${movie.title}",
                modifier = Modifier
                    .width(150.dp)
                    .wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(12.dp))

            InnerTexts(
                text = "Directed by: ${movie.director}"
            )

            Spacer(modifier = Modifier.height(12.dp))

            InnerTexts(
                text = "Genre: ${movie.genre}"
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = buildAnnotatedString {
                    append("Rating: ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.ExtraBold
                        )
                    ) {
                        append(movie.rating)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            InnerTexts(
                text = "Plot:\n${movie.plot}"
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(movie.images) { imageURL ->
                    MovieImageRow(
                        imageURL = imageURL
                    )
                }
            }

        }
    }
}

@Composable
fun InnerTexts(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
    )
}
