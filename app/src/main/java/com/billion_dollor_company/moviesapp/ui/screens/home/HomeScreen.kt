package com.billion_dollor_company.moviesapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.billion_dollor_company.moviesapp.model.MovieInfo
import com.billion_dollor_company.moviesapp.model.getMovies
import com.billion_dollor_company.moviesapp.ui.components.MovieRow
import com.billion_dollor_company.moviesapp.ui.utils.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
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
                            text = "Movies"
                        )
                    },
                    modifier = Modifier
                        .shadow(elevation = 5.dp),
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                    )
                )
            },
        ) {
            Surface(
                modifier = Modifier
                    .padding(it)
            ) {
                MainContent(navController = navController)
            }
        }
    }
}


@Composable
fun MainContent(
    navController: NavController,
    movieList: List<MovieInfo> = getMovies()
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 12.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(movieList) { movie ->
            MovieRow(movie = movie) {
                navController.navigate(
                    route = Screens.DetailsScreens.name + "/${movie.id}"
                )
            }
        }
    }
}
