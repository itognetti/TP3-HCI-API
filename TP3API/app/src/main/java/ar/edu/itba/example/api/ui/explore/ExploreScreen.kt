package ar.edu.itba.example.api.ui.explore

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.components.RoutineCardList
import ar.edu.itba.example.api.util.getViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun ExploreScreen(
    onNavigateToRoutineDetails: (id:Int) -> Unit,
    onNavigateToExecution: (id:Int) -> Unit,
    orderBy: String,
    viewModel: ExploreViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {
    var finished by remember { mutableStateOf(false) }

    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        launch {
            if (uiState.canGetAllRoutines) {
                viewModel.getCurrentUser()
                    viewModel.getRoutines(orderBy).invokeOnCompletion {
                        finished = true
                }
            }
        }
    }

    val toastError = Toast.makeText(LocalContext.current, uiState.error?.message ?: "", Toast.LENGTH_SHORT)

    LaunchedEffect(key1 = uiState.error){
        launch {
            if(uiState.error != null){
                toastError.show()
            }
        }
    }

    if (finished) {
        Column {
            Text(
                text = stringResource(R.string.search_bar),
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
}












