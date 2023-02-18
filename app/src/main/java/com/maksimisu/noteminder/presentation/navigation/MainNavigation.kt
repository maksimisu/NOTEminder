package com.maksimisu.noteminder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.maksimisu.noteminder.presentation.screens.edit.EditScreen
import com.maksimisu.noteminder.presentation.screens.main.MainScreen
import com.maksimisu.noteminder.presentation.screens.detailed.DetailedScreen

sealed class Screens(val route: String) {
    object MainScreen: Screens("main_screen")
    object DetailedScreen: Screens("note_screen")
    object EditScreen: Screens("edit_screen")
}

@Composable
fun SetupNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.MainScreen.route
    ) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navHostController = navHostController)
        }
        composable(
            route = Screens.DetailedScreen.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.LongType
                    nullable = false
                }
            )
        ) {
            DetailedScreen(navHostController = navHostController, it.arguments?.getLong("id")!!)
        }
        composable(
            route = Screens.EditScreen.route + "?name={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.LongType
                    nullable = false
                    defaultValue = -1
                }
            )
        ) {
            EditScreen(navHostController = navHostController, it.arguments!!.getLong("id"))
        }
    }
}
