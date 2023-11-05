package com.billion_dollor_company.moviesapp.ui.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.billion_dollor_company.moviesapp.ui.screens.details.DetailsScreen
import com.billion_dollor_company.moviesapp.ui.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(
            Screens.HomeScreen.name
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screens.DetailsScreens.name + "/{movie}",
            arguments = listOf(
                navArgument(name = "movie") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                backStackEntry.arguments?.getString("movie")!!
            )
        }
    }
}