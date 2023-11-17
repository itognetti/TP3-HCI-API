package ar.edu.itba.example.api.ui.main

//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import ar.edu.itba.example.api.ui.screens.LoginRegisterScreen

//PROBÉ A VER SI VEÍA LA PANTALLA DEL GORDO

//@Composable
//fun FINSPONavHost(navController: NavHostController){
//    NavHost(
//        navController = navController,
//        startDestination = Screen.HomeScreen.route
//    ) {
//        composable(Screen.LoginRegisterScreen.route) {
//            LoginRegisterScreen()
//        }
//    }
//}



//ESTO HAY QUE MODIFICARLO BASTANTE

//@Composable
//fun FINSPONavHost(
//    navController: NavHostController = rememberNavController(),
//    startDestination: String = "home",
//){
//    val uri = "http://finspo.com"
//    val secureUri = "https://finspo.com"
//
//    NavHost(
//        navController = navController,
//        startDestination = startDestination
//    ){
//        composable("home"){
//            HomeScreen(onNavigateToOtherScreen = { id -> navController.navigate("other/$id") } )
//        }
//        composable(
//            "other/{id}",
//            arguments = listOf(navArgument("id") { type = NavType.IntType }),
//            deepLinks = listOf(navDeepLink { uriPattern = "$uri/other?id={id}"},
//            navDeepLink { uriPattern = "$secureUri/other?id={id}" })
//        ){ route ->
//            OtherScreen(route.arguments?.getInt("id"))
//        }
//    }
//}