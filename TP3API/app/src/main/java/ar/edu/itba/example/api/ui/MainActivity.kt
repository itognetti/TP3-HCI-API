package ar.edu.itba.example.api.ui


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.example.api.navigation.AppNavGraph
import ar.edu.itba.example.api.ui.components.Screen
import ar.edu.itba.example.api.ui.theme.ApiTheme
import ar.edu.itba.example.api.ui.components.BottomBar
import ar.edu.itba.example.api.ui.components.TopBar

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                var orderBy by remember { mutableStateOf("date") }

                // Determinar si se debe mostrar la BottomBar
                val shouldShowBottomBar = currentRoute == Screen.HomeScreen.route
                        || currentRoute == Screen.ExploreScreen.route
                        || currentRoute == Screen.ProfileScreen.route
                Scaffold(
                    topBar = {
                             TopBar(
                                 navController = navController,
                                 onOrderBy = {selection: String -> orderBy = selection}
                             )
                    },
                    bottomBar = {
                        if (shouldShowBottomBar) {
                            BottomBar(
                                currentRoute = currentRoute
                            ) { route ->
                                navController.navigate(route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    },
                    content = {paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = paddingValues.calculateBottomPadding())
                                .padding(top = 10.dp)
                        ) {
                            AppNavGraph(navController = navController, orderBy)
                        }
                    }
                )
            }
        }
    }
}