package ar.edu.itba.example.api.ui.details

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
import ar.edu.itba.example.api.ui.components.CycleEntry
import ar.edu.itba.example.api.ui.components.EmptyState
import kotlinx.coroutines.launch
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.util.getViewModelFactory

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
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )

            Column(modifier = Modifier.fillMaxHeight()) {
                Text(
                    text = stringResource(R.string.details),
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
                    val list = uiState.routineCycles.orEmpty()
                    if (list.isEmpty()) {
                        EmptyState(text = stringResource(id = R.string.empty_routine), Icons.Default.Build)
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(
                                count = list.size,
                                key = { index ->
                                    list[index].id.toString()
                                }
                            ) { index ->
                                CycleEntry(
                                    title = list[index].name,
                                    rounds = list[index].repetitions ?: 0,
                                    onNavigateToCycleDetails = onNavigateToCycleDetails,
                                    cycleId = list[index].id ?: -1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}