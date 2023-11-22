package ar.edu.itba.example.api.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String) {
    object HomeScreen: Screen("Home", Icons.Filled.Home, "home")
    object ExploreScreen: Screen("Explore", Icons.Filled.Search, "explore")
    object ProfileScreen: Screen("Profile", Icons.Filled.AccountCircle, "profile")
}