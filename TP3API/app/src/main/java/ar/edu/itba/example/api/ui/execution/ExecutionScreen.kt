package ar.edu.itba.example.api.ui.execution

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.components.EmptyState
import ar.edu.itba.example.api.ui.components.Timer
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.FOrange
import ar.edu.itba.example.api.ui.theme.Grey
import ar.edu.itba.example.api.ui.theme.White
import ar.edu.itba.example.api.util.getViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Composable
fun ExecutionScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    routineId: String,
    viewModel: ExecutionViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {
    val uiState = viewModel.uiState
    var currentCycleIndex by remember { mutableIntStateOf(0) }
    var currentExerciseIndex by remember { mutableIntStateOf(0) }
    var boca by remember { mutableStateOf(false) }
    var rivar by remember { mutableStateOf(false) }
    var finishedThreads by remember { mutableIntStateOf(0) }
    val mutex = Mutex()

    var advancedModeEnabled by remember { mutableStateOf(false) }

    fun nextCycle() {
        if (currentCycleIndex + 1 >= uiState.routineCycles.orEmpty().size) {
            onNavigateToHome()
        } else {
            currentExerciseIndex = 0
            do {
                currentCycleIndex++
            } while (currentCycleIndex < uiState.routineCycles.orEmpty().size && uiState.cycleExercises[uiState.routineCycles!!.getOrNull(currentCycleIndex)!!.id].orEmpty().isEmpty()
            )
            if (currentCycleIndex + 1 >= uiState.routineCycles.orEmpty().size)
               onNavigateToHome()
        }
    }

    fun nextExercise() {
        if (currentExerciseIndex + 1 >= uiState.cycleExercises[uiState.routineCycles!!.getOrNull(currentCycleIndex)!!.id].orEmpty().size) {
            nextCycle()
        } else {
            currentExerciseIndex++
        }
    }

    fun previousCycle() {
        if (currentCycleIndex - 1 < 0) {
            return
        } else {
            var i=1
            while (currentCycleIndex-i >= 0 && uiState.cycleExercises[uiState.routineCycles!!.getOrNull(
                    currentCycleIndex - i
                )!!.id].orEmpty().isEmpty()
            ){
                i++
            }
            //Encontró un cyclo
            if (currentCycleIndex-i >= 0) {
                currentCycleIndex -= i
                currentExerciseIndex =  uiState.cycleExercises[uiState.routineCycles!!.getOrNull(currentCycleIndex)!!.id].orEmpty().size-1
            }
        }
    }

    fun previousExercise() {
        if (currentExerciseIndex - 1 < 0) {
            previousCycle()
        } else {
            currentExerciseIndex--
        }
    }

    val msg = stringResource(id = R.string.empty_cycle)

    LaunchedEffect(key1 = Unit) {
        launch {
            if (uiState.canGetData) {
                viewModel.getRoutineCycles(routineId.toInt()).invokeOnCompletion {
                    boca = true
                }
            }
        }
    }

    LaunchedEffect(key1 = boca) {
        launch {
            if (boca) {
                uiState.routineCycles?.forEach {
                    viewModel.getCycleExercises(it.id!!)
                    mutex.withLock {
                        finishedThreads++
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = finishedThreads) {
        launch {
            if (finishedThreads == uiState.routineCycles?.size) {
                rivar = true
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

    val cycles = uiState.routineCycles
    val currentCycle = uiState.routineCycles?.getOrNull(currentCycleIndex)


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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (rivar) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp)
                                .padding(top = 3.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable { onNavigateBack() },
                                tint = White
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.advanced_mode),
                                color = White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                            )

                            Spacer(modifier = Modifier.padding(horizontal = 6.dp))

                            Switch(
                                checked = advancedModeEnabled,
                                onCheckedChange = { advancedModeEnabled = it },
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                        }

                        if (!advancedModeEnabled) {
                            //Nombre del ciclo
                            if (uiState.cycleExercises[uiState.routineCycles!!.getOrNull(
                                    currentCycleIndex
                                )?.id]
                                    .orEmpty().isEmpty()
                            ) {
                                EmptyState(
                                    text = stringResource(id = R.string.empty_routine),
                                    Icons.Default.Build
                                )
                            } else {
                                Text(
                                    text = currentCycle?.name ?: "Cycle",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Black
                                )
                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 50.dp, vertical = 10.dp),
                                    thickness = 4.dp,
                                    color = Black
                                )
                                Text(
                                    text = uiState.cycleExercises.getOrDefault(
                                        uiState.routineCycles.getOrNull(
                                            currentCycleIndex
                                        )?.id ?: -1, null
                                    )
                                        ?.get(currentExerciseIndex)?.exercise?.name
                                        ?: "Titulo por defecto",
                                    color = FOrange,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight(500)
                                )
                                val reps: String
                                val numReps: Int = uiState.cycleExercises.getOrDefault(
                                    uiState.routineCycles.getOrNull(currentCycleIndex)?.id ?: -1,
                                    null
                                )
                                    ?.get(currentExerciseIndex)?.repetitions ?: 0
                                reps = if (numReps == 0) {
                                    "--"
                                } else {
                                    numReps.toString()
                                }

                                Text(
                                    text = "Reps: $reps",
                                    color = White,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight(600),
                                    modifier = Modifier
                                           .padding(top = 6.dp)
                                )

                                Box(
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Timer(
                                        totalTime = uiState.cycleExercises.getOrDefault(
                                            uiState.routineCycles.getOrNull(
                                                currentCycleIndex
                                            )?.id ?: -1, null
                                        )
                                            ?.get(currentExerciseIndex)?.duration?.times(1000L)
                                            ?: 0,
                                        inactiveBarColor = Grey,
                                        activeBarColor = White,
                                        modifier = Modifier
                                            .size(280.dp),
                                        nextFunc = { nextExercise() },
                                        prevFunc = { previousExercise() },
                                        hasPrev = currentCycleIndex > 0 || currentExerciseIndex > 0,
                                        strokeWidth = 10.dp
                                    )
                                }
                            }
                        } else {
                            //Nombre del ciclo
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Box {
                                        Column(
                                            modifier = Modifier,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = currentCycle?.name ?: "Cycle",
                                                fontSize = 32.sp,
                                                fontWeight = FontWeight(500),
                                                color = Black
                                            )
                                            Divider(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 50.dp, vertical = 10.dp),
                                                thickness = 4.dp,
                                                color = Black
                                            )
                                        }
                                    }

                                    Card(
                                        modifier = Modifier
                                            .width(380.dp)
                                            .padding(16.dp),
                                        shape = RoundedCornerShape(16.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                        ) {

                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(Black)
                                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.End
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Refresh,
                                                    contentDescription = null,
                                                    tint = White,
                                                    modifier = Modifier
                                                        .size(24.dp),
                                                )

                                                Spacer(modifier = Modifier.width(100.dp))

                                                Icon(
                                                    imageVector = Icons.Default.Alarm,
                                                    contentDescription = null,
                                                    tint = White,
                                                    modifier = Modifier
                                                        .size(24.dp),
                                                )

                                                Spacer(modifier = Modifier.width(32.dp))
                                            }

                                            LazyColumn(
                                                modifier = Modifier
                                                    .padding(16.dp),
                                                verticalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                val list = uiState.cycleExercises[uiState.routineCycles!!.getOrNull(currentCycleIndex)?.id!!].orEmpty()
                                                items(
                                                    count = list.size,
                                                    key = { index -> list[index].exercise.name!! }
                                                ) { index ->
                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.SpaceBetween
                                                    ) {
                                                        Text(
                                                            text = list[index].exercise.name!!,
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 20.sp,
                                                            color = Black
                                                        )
                                                        Text(
                                                            text = list[index].repetitions.toString() + " reps.",
                                                            fontSize = 18.sp,
                                                            color = Black
                                                        )
                                                        Text(
                                                            text = list[index].duration.toString() + " s.",
                                                            fontSize = 18.sp,
                                                            color = Black
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(MaterialTheme.colorScheme.background),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        fun nextC() {
                                            if (currentCycleIndex + 1 == uiState.routineCycles!!.size) {
                                                onNavigateToHome()
                                            } else {
                                                currentCycleIndex++
                                            }
                                        }

                                        Spacer(modifier = Modifier.width(16.dp))

                                        Button(
                                            colors = ButtonDefaults.buttonColors(Transparent),
                                            onClick = { if (currentCycleIndex > 0) currentCycleIndex-- },
                                        ) {
                                            Icon(
                                                Icons.Default.ArrowBack,
                                                contentDescription = "Previous",
                                                tint = FOrange,
                                                modifier = Modifier.size(36.dp)
                                            )
                                        }

                                        Button(
                                            colors = ButtonDefaults.buttonColors(Transparent),
                                            onClick = { nextC() },
                                        ) {
                                            Icon(
                                                Icons.Default.ArrowForward,
                                                contentDescription = "Next",
                                                tint = FOrange,
                                                modifier = Modifier.size(36.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = stringResource(R.string.loading_message),
                        color = White
                    )
                }
            }
        }
    }
}