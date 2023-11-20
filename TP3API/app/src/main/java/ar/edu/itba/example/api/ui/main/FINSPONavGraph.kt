package ar.edu.itba.example.api.ui.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.example.api.ui.screens.AboutUs
import ar.edu.itba.example.api.ui.screens.HomeScreen
import ar.edu.itba.example.api.ui.screens.LoginRegisterScreen
import ar.edu.itba.example.api.ui.screens.LoginScreen
import ar.edu.itba.example.api.ui.screens.ProfileScreen
import ar.edu.itba.example.api.ui.screens.SearchScreen
import ar.edu.itba.example.api.util.getViewModelFactory

@Composable
fun FINSPONavGraph(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginRegisterScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(viewModel)
        }

        composable(Screen.SearchScreen.route) {
            SearchScreen()
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen(
                onNavigateToLoginRegisterScreen = {navController.navigate(Screen.LoginRegisterScreen.route)},
                viewModel
            )
        }

        composable(Screen.LoginRegisterScreen.route) {
            LoginRegisterScreen(
                onNavigateToLoginScreen = {navController.navigate(Screen.LoginScreen.route)},
                onNavigateToAboutUs = {navController.navigate(Screen.AboutUs.route)}
            )
        }

        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigateToHomeScreen = {navController.navigate(Screen.HomeScreen.route)},
                viewModel
            )
        }

        composable(Screen.AboutUs.route) {
            AboutUs(
                onNavigateToLoginRegisterScreen = {navController.navigate(Screen.LoginRegisterScreen.route)}
            )
        }
    }
}