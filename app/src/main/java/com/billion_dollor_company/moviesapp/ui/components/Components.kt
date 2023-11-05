package com.billion_dollor_company.moviesapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.billion_dollor_company.moviesapp.model.MovieInfo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRow(
    movie: MovieInfo,
    onItemClick: (String) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        onClick = {
            onItemClick(movie.title)
        },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .width(100.dp),
                shape = RectangleShape,
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.poster)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Poster of the movie named: ${movie.title}",
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(
                    text = movie.title,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "By ${movie.director}",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "Genre: ${movie.genre}",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    buildAnnotatedString {
                        append("Rating: ")
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.ExtraBold
                            )
                        ) {
                            append(movie.rating)
                        }
                    }
                )
                AnimatedVisibility(visible = expanded) {
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Plot:")
                        Text(text = movie.plot)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    IconButton(
                        onClick = {
                            expanded = !expanded
                        },
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector =
                            if (!expanded)
                                Icons.Filled.KeyboardArrowDown
                            else
                                Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Down Arrow",
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun MovieImageRow(imageURL: String) {
    Card(
        modifier = Modifier
            .height(150.dp),
        shape = RectangleShape
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageURL)
                .crossfade(true)
                .build(),
            contentDescription = "Image of the movie",
        )
    }
}