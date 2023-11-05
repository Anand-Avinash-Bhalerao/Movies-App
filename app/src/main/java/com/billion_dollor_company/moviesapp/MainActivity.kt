package com.billion_dollor_company.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.billion_dollor_company.moviesapp.ui.theme.MoviesAppTheme
import com.billion_dollor_company.moviesapp.ui.utils.MovieNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}


@Composable
fun MyApp(
    content: @Composable () -> Unit
) {
    MoviesAppTheme {
        content()
    }
}
