package com.ucb.examen.navigation

sealed class Screen(val route: String) {
    object Search : Screen("search")
    object Favorites : Screen("favorites")
}
