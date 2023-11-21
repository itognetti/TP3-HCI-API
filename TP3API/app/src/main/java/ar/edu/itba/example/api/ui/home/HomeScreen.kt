package ar.edu.itba.example.api.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.components.RoutineCardList
import ar.edu.itba.example.api.util.getViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onNavigateToRoutineDetails: (id:Int) -> Unit,
    onNavigateToExecution: (id:Int) -> Unit,
    onNavigateToLogin: () -> Unit,
    orderBy: String,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {
    val uiState = viewModel.uiState
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val toastError = Toast.makeText(LocalContext.current, uiState.error?.message?: "", Toast.LENGTH_SHORT)

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.CREATED) {
            launch {
                //onPopStack("login")
                if (!uiState.isAuthenticated)
                    onNavigateToLogin()
            }
        }
    }

    LaunchedEffect(key1 = uiState.error) {
        launch {
            if (uiState.error != null) {
                toastError.show()
            }
        }
    }

    LaunchedEffect(key1 = orderBy) {
        launch {
            if (uiState.canGetAllRoutines) {
                viewModel.getRoutines(orderBy)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        launch {
            if (uiState.canGetCurrentUser)
                viewModel.getCurrentUser()
        }
    }

    Column {
        Text(
            text = stringResource(R.string.routines),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp)
        )

        if (uiState.isFetching) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.loading_message),
                    fontSize = 16.sp
                )
            }
        } else {
            RoutineCardList(
               list = uiState.routines?.filter { routine -> routine.user?.username == uiState.currentUser?.username }.orEmpty(),
                onNavigateToRoutineDetails = onNavigateToRoutineDetails,
                onNavigateToExecution = onNavigateToExecution
             )
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}

/*

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.components.CardItem
import ar.edu.itba.example.api.ui.main.MainViewModel
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.FOrange
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(5.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logotext),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )

        Text(
            text = stringResource(id = R.string.Myroutines),
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = FOrange,
        )
        Log.d("HomeScreen", "Number of routines: ${viewModel.uiState.routines?.size}")

        if (viewModel.uiState.isFetching) {
            // Mostrar la barra de progreso mientras se cargan los datos
            CircularProgressIndicator(color = FOrange)
        } else {
            if (viewModel.uiState.routines != null) {
                //uiState.users?.data.orEmpty()
                // Mostrar la LazyColumn si las rutinas no son nulas
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = viewModel.uiState.isFetching),
                    onRefresh = { viewModel.getFilteredRoutines() },
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.91f)
                            .padding(bottom = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                    ) {
                        itemsIndexed(
                            viewModel.uiState.routines.orEmpty()
                        ) { index, routine ->
                            CardItem(imageResId = R.drawable.gym1, title = routine.name, description = routine.detail.orEmpty())
                        }
                    }
                }
            } else {
                Text(
                    text = stringResource(R.string.no_routines),
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                )
            }
        }


        // Lista de tarjetas
        CardItem(imageResId = R.drawable.gym1, title = "Tarjeta 1", description = "Descripción de la tarjeta 1")
        CardItem(imageResId = R.drawable.gym2, title = "Tarjeta 2", description = "Descripción de la tarjeta 2")
        CardItem(imageResId = R.drawable.gym3, title = "Tarjeta 3", description = "Descripción de la tarjeta 3")
        CardItem(imageResId = R.drawable.gym4, title = "Tarjeta 4", description = "Descripción de la tarjeta 4")
        CardItem(imageResId = R.drawable.gym5, title = "Tarjeta 5", description = "Descripción de la tarjeta 5")


    }
}

*/