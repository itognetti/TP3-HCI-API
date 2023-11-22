package ar.edu.itba.example.api.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.main.MainViewModel
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.FOrange
import ar.edu.itba.example.api.ui.theme.Grey
import ar.edu.itba.example.api.ui.theme.White
import ar.edu.itba.example.api.util.getViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController : NavHostController,
    onOrderBy: (String) -> Unit,
    viewModel :MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {
    val uiState = viewModel.uiState
    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute = backStackEntry?.destination?.route?: "home"
    if (currentRoute.contains("details")){
        currentRoute = "details"
    }

    val home = stringResource(R.string.home_screen)
    val explore = stringResource(R.string.search_screen)
    val details = stringResource(R.string.routine_details)

    val topBarMap by remember {
        mutableStateOf(hashMapOf(
            "home" to TopBarInfo(home, hasBackArrow = false, hasOrderBy = true),
            "explore" to TopBarInfo(explore, hasBackArrow = false, hasOrderBy = true),
            "details" to TopBarInfo(details, hasBackArrow = true, hasOrderBy = false)
        ))
    }

    val currentTopBar = topBarMap[currentRoute]
    var showPopUp by remember { mutableStateOf(false) }

    val toastError = Toast.makeText(LocalContext.current, uiState.error?.message ?: "", Toast.LENGTH_SHORT)

    LaunchedEffect(key1 = uiState.error){
        if(uiState.error != null){
            toastError.show()
        }
    }

    if(currentTopBar != null){
        TopAppBar(
            title = { Text(text = currentTopBar.title, color = White) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Black
            ),
            navigationIcon = {
                if (currentTopBar.hasBackArrow) {
                    IconButton(
                        onClick = { navController.popBackStack() }){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            tint = White)
                    }
                }
            },
            actions = {
                if (currentTopBar.hasOrderBy) {
                    Row(
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .clickable { showPopUp = !showPopUp }
                    ) {
                        Text(
                            text = stringResource(id = R.string.order_by),
                            color = White,
                            modifier = Modifier
                                .padding(end = 6.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "orderBy",
                            tint = White
                        )
                    }
                    DropdownMenu(
                        expanded = showPopUp,
                        onDismissRequest = { showPopUp = false },
                        modifier = Modifier.background(Black)
                    ) {
                        DropdownMenuItem(
                            text = { Text(
                                text = stringResource(R.string.date),
                                color = White
                            ) },
                            onClick = { onOrderBy("date"); showPopUp = false },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "date",
                                    tint = White
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(
                                text = stringResource(R.string.difficulty),
                                color = White) },
                            onClick = { onOrderBy("difficulty"); showPopUp = false },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Build,
                                    contentDescription = "difficulty",
                                    tint = White
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(
                                text = stringResource(R.string.score),
                                color = White) },
                            onClick = { onOrderBy("score"); showPopUp = false },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "score",
                                    tint = White
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(
                                text = stringResource(R.string.category),
                                color = White) },
                            onClick = { onOrderBy("category"); showPopUp = false },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "category",
                                    tint = White
                                )
                            }
                        )
                    }
                }
            },
            )
    }
}