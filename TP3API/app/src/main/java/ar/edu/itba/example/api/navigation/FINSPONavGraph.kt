package ar.edu.itba.example.api.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.example.api.ui.main.MainViewModel
import ar.edu.itba.example.api.ui.screens.AboutUs
import ar.edu.itba.example.api.ui.home.HomeScreen
import ar.edu.itba.example.api.ui.profile.ProfileScreen
import ar.edu.itba.example.api.ui.login.LoginScreen


@Composable
fun FINSPONavGraph(navController: NavHostController,
    orderBy: String){
    NavHost(
        navController = navController,
        startDestination = "login"
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

        composable("login") {
            LoginScreen(
                onNavigateToHomeScreen = { navController.navigate(Screen.HomeScreen.route) },
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

/*
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    val uri = "http://www.example.com"
    val secureUri = "https://www.example.com"

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToOtherScreen = { id -> navController.navigate("other/$id") }
            )
        }
        composable(
            "other/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/other?id={id}" },
                navDeepLink { uriPattern = "$secureUri/other?id={id}" })
        ) {
            OtherScreen(it.arguments?.getInt("id"))
        }
    }
}
 */