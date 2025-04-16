package com.ucb.examen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.examen.book.BookSearchUI
import com.ucb.examen.book.FavoritesUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
            BookSearchUI(onGoToFavorites = {
                navController.navigate(Screen.Favorites.route)
            })
        }
        composable(Screen.Favorites.route) {
            FavoritesUI(onBack = {
                navController.popBackStack()
            })
        }
    }
}
