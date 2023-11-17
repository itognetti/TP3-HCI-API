package ar.edu.itba.example.api.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.example.api.ui.screens.AboutUs
import ar.edu.itba.example.api.ui.screens.HomeScreen
import ar.edu.itba.example.api.ui.screens.LoginRegisterScreen
import ar.edu.itba.example.api.ui.screens.LoginScreen
import ar.edu.itba.example.api.ui.screens.ProfileScreen
import ar.edu.itba.example.api.ui.screens.RegisterScreen
import ar.edu.itba.example.api.ui.screens.SearchScreen
import ar.edu.itba.example.api.ui.screens.SecurScreen

@Composable
fun FINSPONavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginRegisterScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen()
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(
                onNavegateTologinRegisterScreen = {navController.navigate(Screen.LoginRegisterScreen.route)}
            )
        }
        composable(Screen.LoginRegisterScreen.route) {
            LoginRegisterScreen(
                onNavegateToLoginScreen = {navController.navigate(Screen.LoginScreen.route)},
                onNavegateToRegisterScreen = {navController.navigate(Screen.RegisterScreen.route)},
                onNavegateToAboutUs = {navController.navigate(Screen.AboutUs.route)}
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavegateToHomeScreen = {navController.navigate(Screen.HomeScreen.route)}
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                onNavegateToSecurScreen = {navController.navigate(Screen.SecurScreen.route)}
            )
        }

        composable(Screen.SecurScreen.route) {
            SecurScreen(
                onNavegateToHomeScreen = {navController.navigate(Screen.HomeScreen.route)}
            )
        }

        composable(Screen.AboutUs.route) {
            AboutUs(
                onNavegateTologinRegisterScreen = {navController.navigate(Screen.LoginRegisterScreen.route)}
            )
        }
    }
}