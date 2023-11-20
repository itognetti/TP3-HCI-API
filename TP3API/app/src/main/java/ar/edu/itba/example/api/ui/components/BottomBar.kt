package ar.edu.itba.example.api.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ar.edu.itba.example.api.navigation.Screen
import ar.edu.itba.example.api.ui.theme.FOrange
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.White

@Composable
fun BottomBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.ExploreScreen,
        Screen.ProfileScreen
    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Black
    ) {
        items.forEach { item ->
            NavigationBarItem(
                modifier = Modifier.background(color = Color.Transparent),
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) FOrange else White
                    )
                },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    onNavigateToRoute(item.route)},
            )
        }
    }
}

/*

@Composable
fun BottomBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    val items = listOf(
        Screen.FirstScreen,
        Screen.SecondScreen,
        Screen.ThirdScreen
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = { onNavigateToRoute(item.route) }
            )
        }
    }
}
//                colors = NavigationBarItemColors(
//                    selectedIconColor = FOrange,
//                    selectedTextColor = FOrange,
//                    selectedIndicatorColor = Color.Transparent,
//                    unselectedIconColor = White,
//                    unselectedTextColor = White,
//                ),
 */