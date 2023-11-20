package ar.edu.itba.example.api.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import ar.edu.itba.example.api.ui.details.DetailsScreen
import ar.edu.itba.example.api.ui.execution.ExecutionScreen
import ar.edu.itba.example.api.ui.explore.ExploreScreen
import ar.edu.itba.example.api.ui.home.HomeScreen
import ar.edu.itba.example.api.ui.login.LoginScreen
import ar.edu.itba.example.api.ui.profile.ProfileScreen
import ar.edu.itba.example.api.ui.screens.AboutUsScreen
import com.example.tp3_hci.Screens.CycleDetailsScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController(),
    orderBy: String){
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        val uri = "http://www.finspo.com"
        val secureUri = "https://www.finspo.com"

        composable("home") {
            HomeScreen(
                onNavigateToRoutineDetails = {id -> navController.navigate("details/$id")},
                onNavigateToExecution = {id -> navController.navigate("execution/$id")},
                orderBy = orderBy
            )
        }

        composable("explore") {
            ExploreScreen(
                onNavigateToRoutineDetails = { id -> navController.navigate("details/$id") },
                onNavigateToExecution = { id -> navController.navigate("execution/$id") },
                orderBy = orderBy
            )
        }

        composable("profile") {
            ProfileScreen(
                onNavigateToLogin = {navController.navigate("login")}
            )
        }

        composable("details/{routineId}",
            arguments = listOf(navArgument("routineId") {
                type = NavType.StringType
                defaultValue = "-1"} ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern =  "$uri/{routineId}"},
                navDeepLink {
                    uriPattern = "$secureUri/{routineId}"})
        ){
            DetailsScreen(
                onNavigateToCycleDetails = { id -> navController.navigate("details-cycle/$id")},
                routineId = navController.currentBackStackEntry?.arguments?.getString("routineId")?: "-1"
            )
        }

        composable("details-cycle/{cycleId}"){
            CycleDetailsScreen(
                cycleId = navController.currentBackStackEntry?.arguments?.getString("cycleId")?: "-1")
        }

        composable("login") {
            LoginScreen(
                onNavigateToHomeScreen = { navController.navigate("home") },
                onNavigateToAboutUs = { navController.navigate("about_us")}
            )
        }

        composable("execution/{routineId}"){
            ExecutionScreen(
                onNavigateBack = { navController.navigateUp() },
                // onNavigateToRate = {id -> navController.navigate("review/$id)}, SEGURO LO MANDAMOS AL HOME
                onNavigateToHome = {navController.navigate("home")},
                routineId = navController.currentBackStackEntry?.arguments?.getString("routineId")?: "-1")
        }


        composable("about_us") {
            AboutUsScreen(
                onNavigateToLoginScreen = {navController.navigate("login")}
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