package ar.edu.itba.example.api.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.components.RoutineCardList
import ar.edu.itba.example.api.ui.theme.White
import ar.edu.itba.example.api.util.getViewModelFactory
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
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

    SwipeRefresh(
        state = rememberSwipeRefreshState(uiState.isFetching),
        onRefresh = {viewModel.getRoutines(orderBy)}
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top=43.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.isFetching) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.loading_message),
                            fontSize = 16.sp,
                            color = White
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
    }
}