package ar.edu.itba.example.api.ui.cycleDetails

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.ui.components.EmptyState
import ar.edu.itba.example.api.ui.components.ExerciseEntry
import kotlinx.coroutines.launch
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.theme.White
import ar.edu.itba.example.api.util.getViewModelFactory

@Composable
fun CycleDetailsScreen(
    cycleId: String,
    viewModel: CycleDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        launch {
            if(uiState.canGetAllCycleExercises)
                viewModel.getCycleExercises(cycleId.toInt())
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

    Box(
        modifier = Modifier
            .fillMaxSize()
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
                .padding(top = 65.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column {
                //Titulo
                Text(
                    text = stringResource(R.string.cycle_details),
                    fontSize = 22.sp,
                    color = White,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp),
                )
                //Tabla de ejercicios
                if (uiState.isFetching) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.loading_message),
                            fontSize = 16.sp
                        )
                    }
                } else {
                    val list = uiState.cycleExercises.orEmpty()
                    if (list.isEmpty()) {
                        EmptyState(
                            text = stringResource(R.string.empty_cycle),
                            imgVector = Icons.Default.Build
                        )
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(
                                count = list.size,
                                key = { index -> index }
                            ) { index ->
                                ExerciseEntry(
                                    title = list[index].exercise.name ?: "Error",
                                    reps = list[index].repetitions ?: 0,
                                    time = list[index].duration ?: 0,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}