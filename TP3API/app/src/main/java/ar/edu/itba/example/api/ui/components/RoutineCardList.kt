package ar.edu.itba.example.api.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.R

@Composable
fun RoutineCardList(
    list: List<Routine>,
    onNavigateToRoutineDetails: (id:Int) -> Unit,
    onNavigateToExecution: (id:Int) -> Unit,
) {

    val imgIds by remember { mutableStateOf(listOf(R.drawable.gym1, R.drawable.gym2, R.drawable.gym3, R.drawable.gym4, R.drawable.gym5 )) }

    if (list.isEmpty()){
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                ){
            EmptyState(text = stringResource(id = R.string.no_routines), imgVector = Icons.Default.Build)
        }
    } else {
        LazyVerticalGrid(
            state = rememberLazyGridState(),
            modifier = Modifier
                .padding(horizontal = 8.dp),
            columns = GridCells.Adaptive(minSize = 250.dp)
        ) {
            items(
                count = list.size,
                key = { index ->
                    list[index].id.toString()
                }
            ) { index ->
                RoutineCard(
                    name = list[index].name,
                    description = list[index].detail ?: "",
                    id = list[index].id!!,
                    imgId = imgIds[index%5],
                    onNavigateToRoutineDetails = onNavigateToRoutineDetails,
                    onNavigateToExecution = onNavigateToExecution
                )
            }
        }
    }

}