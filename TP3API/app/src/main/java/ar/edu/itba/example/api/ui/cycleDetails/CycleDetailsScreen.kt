package ar.edu.itba.example.api.ui.cycleDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.ui.components.EmptyState
import ar.edu.itba.example.api.ui.components.ExerciseEntry
import kotlinx.coroutines.launch
import ar.edu.itba.example.api.R


@Composable
fun CycleDetailsScreen(
    cycleId: String,
    viewModel: CycleDetailsViewModel
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        launch {
            if(uiState.canGetAllCycleExercises)
                viewModel.getCycleExercises(cycleId.toInt())
        }
    }

//    val toastError = Toast.makeText(LocalContext.current, uiState.error, Toast.LENGTH_SHORT)
//
//    LaunchedEffect(key1 = uiState.error){
//        launch {
//            if(uiState.error != null){
//                toastError.show()
//            }
//        }
//    }

    Column() {
        //Titulo
        Text(
            text = stringResource(R.string.cycle),
            fontSize = 22.sp,
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
                    text = stringResource(id = R.string.loading_message),
                    fontSize = 16.sp
                )
            }
        } else {
            val list = uiState.cycleExercises?.orEmpty()
            if (list==null || list.isEmpty()){
                EmptyState(text = stringResource(id = R.string.empty_cycle), imgVector = Icons.Default.Build)
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        count = list?.size?:0,
                        key = { index -> index }
                    ) { index ->
                        ExerciseEntry(
                            title = list?.get(index)?.exercise?.name ?: "Error",
                            reps = list?.get(index)?.repetitions?:0,
                            time = list?.get(index)?.duration?:0,
                        )
                    }
                }
            }
            
        }
    }
}