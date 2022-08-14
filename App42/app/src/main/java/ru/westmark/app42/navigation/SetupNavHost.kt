package ru.westmark.app42.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.westmark.app42.screens.FilterScreen
import ru.westmark.app42.screens.MainScreen
import ru.westmark.app42.screens.SplashScreen
import ru.westmark.app42.screens.SplashScreenNotConnected
import ru.westmark.app42.screens.view_models.EmployerViewModel
import ru.westmark.app42.utils.Constants

sealed class Screens(val route: String) {
    object SplashScreenNoConnection : Screens(route = Constants.Screens.SPLASH_SCREEN_NO_CONNECTION)
    object SplashScreen : Screens(route = Constants.Screens.SPLASH_SCREEN)
    object MainScreen : Screens(route = Constants.Screens.MAIN_SCREEN)
    object FilterScreen : Screens(route = Constants.Screens.FILTER_SCREEN)
}

@Composable
fun SetupNavHost(
    navHostController: NavHostController,
    employerViewModel: EmployerViewModel
) {
    NavHost(navController = navHostController, startDestination = Screens.SplashScreen.route) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(
                navHostController = navHostController,
                employerViewModel = employerViewModel
            )
        }
        composable(route = Screens.MainScreen.route) {
            MainScreen(employerViewModel = employerViewModel, navHostController = navHostController)
        }
        composable(route = Screens.FilterScreen.route) {
            FilterScreen(
                employerViewModel = employerViewModel,
                navHostController = navHostController
            )
        }
        composable(route = Screens.SplashScreenNoConnection.route) {
            SplashScreenNotConnected(
                navHostController = navHostController,
                employerViewModel = employerViewModel
            )
        }
    }
}