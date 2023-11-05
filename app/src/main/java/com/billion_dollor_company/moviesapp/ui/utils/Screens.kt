package com.billion_dollor_company.moviesapp.ui.utils

enum class Screens {
    HomeScreen,
    DetailsScreens;

    companion object {
        fun fromRoute(route: String?): Screens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailsScreens.name -> DetailsScreens
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route was not recognised!")
        }
    }
}