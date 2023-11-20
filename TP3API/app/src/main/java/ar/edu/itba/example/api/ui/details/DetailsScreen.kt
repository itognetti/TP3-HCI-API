package ar.edu.itba.example.api.ui.details

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HideSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.ui.components.CicleEntry
import ar.edu.itba.example.api.ui.components.EmptyState
import com.example.tp3_hci.util.getViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(
    onNavigateToCycleDetails: (id:Int) -> Unit,
    routineId: String,
    viewModel: DetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        launch {
            if(uiState.canGetAllRoutineCycles)
                viewModel.getRoutineCycles(routineId.toInt())
        }
    }

    val toastError = Toast.makeText(LocalContext.current, uiState.message, Toast.LENGTH_SHORT)

    LaunchedEffect(key1 = uiState.message){
        launch {
            if(uiState.message != null){
                toastError.show()
            }
        }
    }

    Column(modifier = Modifier.fillMaxHeight()) {
        //Titulo
        Text(
            text = stringResource(R.string.details_subtitle),
            fontSize = 22.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp),
        )
        //Tabla de ciclos
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
            val list = uiState.routineCycles?.orEmpty()
            if (list==null || list.isEmpty()){
                EmptyState(text = stringResource(id = R.string.empty_routine), Icons.Default.HideSource)
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        count = list?.size?:0,
                        key = { index ->
                            list?.get(index)?.id.toString()
                        }
                    ) { index ->
                        CicleEntry(
                            title = list?.get(index)?.name ?: "Error",
                            rounds = list?.get(index)?.repetitions?:0,
                            onNavigateToCycleDetails = onNavigateToCycleDetails,
                            cycleId = list?.get(index)?.id?:-1
                        )
                    }
                }
            }
        }
    }
}

/*
@Composable
fun ExampleRoutineScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(Black)
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardItem(
                imageResId = R.drawable.gym1,
                title = stringResource(id = R.string.Rout1),
                description = stringResource(id = R.string.Desc1)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(66.dp))
            ExerciseItem(stringResource(id = R.string.Exercise1))
            ExerciseItem(stringResource(id = R.string.Exercise2))
            ExerciseItem(stringResource(id = R.string.Exercise3))
            Spacer(modifier = Modifier.height(16.dp))
            // Botón de Inicio
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(FOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.StartRoutine),
                    fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
 */